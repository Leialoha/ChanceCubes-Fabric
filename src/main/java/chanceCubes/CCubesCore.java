package chanceCubes;

import chanceCubes.blocks.CCubesBlocks;
import chanceCubes.client.ClientHelper;
import chanceCubes.commands.CCubesRewardArguments;
import chanceCubes.commands.CCubesServerCommands;
import chanceCubes.config.CCubesSettings;
import chanceCubes.config.ConfigLoader;
import chanceCubes.config.CustomRewardsLoader;
import chanceCubes.items.CCubesItems;
import chanceCubes.listeners.PlayerConnectListener;
import chanceCubes.listeners.TickListener;
import chanceCubes.listeners.WorldGen;
import chanceCubes.modifier.CCubesModifiers;
import chanceCubes.network.CCubesNetwork;
import chanceCubes.rewards.DefaultGiantRewards;
import chanceCubes.rewards.DefaultRewards;
import chanceCubes.sounds.CCubesSounds;
import chanceCubes.util.NonreplaceableBlockOverride;
import chanceCubes.util.StatsRegistry;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class CCubesCore implements ModInitializer {
	public static final String MODID = "chancecubes";
	public static final Logger logger = LogManager.getLogger(MODID);
	public static ConfigHolder<CCubesSettings> CONFIG;

	@Override
	public void onInitialize() {
		CCubesNetwork.init();
		AutoConfig.register(CCubesSettings.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(CCubesSettings.class);
		CCubesBlocks.register();
		CCubesItems.register();
	}

	public CCubesCore() {
		try {
			SSLContext context = SSLContext.getInstance("TLSv1.2");
			TrustManager[] trustManager = new TrustManager[] {
					new X509TrustManager() {
						public X509Certificate[] getAcceptedIssuers() {
							return new X509Certificate[0];
						}

						public void checkClientTrusted(X509Certificate[] certificate, String str) {
						}

						public void checkServerTrusted(X509Certificate[] certificate, String str) {
						}
					}
			};
			context.init(null, trustManager, new SecureRandom());

			HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		CCubesItems.CREATIVE_MODE_TABS.register(eventBus);
		CCubesSounds.SOUNDS.register(eventBus);
		CCubesModifiers.BIOME_MODIFIER_SERIALIZERS.register(eventBus);
		CCubesRewardArguments.COMMAND_ARGUMENT_TYPES.register(eventBus);
		WorldGen.FEATURES.register(eventBus);
		eventBus.addListener(this::commonStart);
		eventBus.addListener(this::onIMCMessage);
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHelper::clientStart);
			eventBus.addListener(ClientHelper::onEntityRenders);
			MinecraftForge.EVENT_BUS.addListener(ClientHelper::onClientCommandsRegister);
		});
		MinecraftForge.EVENT_BUS.register(this);
		ConfigLoader.initParentFolder();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigLoader.configSpec,
				"chancecubes" + File.separatorChar + "chancecubes-server.toml");
	}

	public void commonStart(FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new PlayerConnectListener());
		MinecraftForge.EVENT_BUS.register(new TickListener());
		event.enqueueWork(StatsRegistry::init);
	}

	@SubscribeEvent
	public void lootTableLoad(LootTableLoadEvent event) {
		if (CONFIG.get().chestLoot && event.getName().getPath().contains("chests"))
			event.getTable().addPool(LootPool.lootPool()
					/* .name("chance_cubes_cubes") */.add(LootItem.lootTableItem(CCubesItems.CHANCE_CUBE)).build());
	}

	@SubscribeEvent
	public void serverStart(ServerStartingEvent event) {
		CCubesSettings.backupNRB.add(Blocks.BEDROCK.defaultBlockState());
		CCubesSettings.backupNRB.add(Blocks.OBSIDIAN.defaultBlockState());
		DefaultRewards.loadDefaultRewards();
		DefaultGiantRewards.loadDefaultRewards();
		CustomRewardsLoader.instance.loadCustomRewards();
		NonreplaceableBlockOverride.loadOverrides();

		logger.log(Level.INFO, "Death and destruction prepared! (And Cookies. Cookies were also prepared.)");
	}

	@SubscribeEvent
	public void onCommandsRegister(RegisterCommandsEvent event) {
		new CCubesServerCommands(event.getDispatcher());
	}

	public void onIMCMessage(InterModProcessEvent e) {
		e.getIMCStream().forEach((message) -> {
			Logger logger = LogManager.getLogger(MODID);
			if (message.method().equalsIgnoreCase("add-nonreplaceable")) {
				Object obj = message.messageSupplier().get();
				if (obj instanceof BlockState state) {
					CCubesSettings.nonReplaceableBlocksIMC.add(state);
					logger.info(message.senderModId() + " has added the blockstate of \"" + state
							+ "\" that Chance Cubes rewards will no longer replace.");
				}
			}
		});
	}

}
