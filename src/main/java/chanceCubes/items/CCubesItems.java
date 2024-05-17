package chanceCubes.items;

import chanceCubes.CCubesCore;
import chanceCubes.blocks.CCubesBlocks;
import chanceCubes.containers.CreativePendantContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MenuType.MenuSupplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab.Row;

import java.util.ArrayList;
import java.util.List;

public class CCubesItems
{
	protected static final List<Item> ENTRIES = new ArrayList<>();

	public static final ItemChancePendant CHANCE_PENDANT_T1 = registerItems("chance_pendant_tier1", new ItemChancePendant(10));
	public static final ItemChancePendant CHANCE_PENDANT_T2 = registerItems("chance_pendant_tier2", new ItemChancePendant(25));
	public static final ItemChancePendant CHANCE_PENDANT_T3 = registerItems("chance_pendant_tier3", new ItemChancePendant(50));

	public static final ItemSilkTouchPendant SILK_PENDANT = registerItems("silk_touch_pendant", new ItemSilkTouchPendant());
	public static final BaseChanceCubesItem CREATIVE_PENDANT = registerItems("creative_pendant", new ItemCreativePendant());
	public static final BaseChanceCubesItem REWARD_SELECTOR_PENDANT = registerItems("reward_selector_pendant", new ItemRewardSelectorPendant());
	public static final BaseChanceCubesItem SINGLE_USE_REWARD_SELECTOR_PENDANT = registerItems("single_use_reward_selector_pendant", new ItemSingleUseRewardSelectorPendant());

	public static final BaseChanceCubesItem SCANNER = registerItems("scanner", new ItemScanner());

	public static final ItemChanceCube CHANCE_CUBE = registerItems("chance_cube", new ItemChanceCube(CCubesBlocks.CHANCE_CUBE));
	public static final ItemChanceCube CHANCE_ICOSAHEDRON = registerItems("chance_icosahedron", new ItemChanceCube(CCubesBlocks.CHANCE_ICOSAHEDRON));
	public static final ItemChanceCube GIANT_CUBE = registerItems("giant_chance_cube", new ItemChanceCube(CCubesBlocks.GIANT_CUBE));
	public static final ItemChanceCube COMPACT_GIANT_CUBE = registerItems("compact_giant_chance_cube", new ItemChanceCube(CCubesBlocks.COMPACT_GIANT_CUBE));
	public static final ItemChanceCube CUBE_DISPENSER = registerItems("cube_dispenser", new ItemChanceCube(CCubesBlocks.CUBE_DISPENSER));

	public static MenuType<CreativePendantContainer> CREATIVE_PENDANT_CONTAINER = registerMenuType("creative_pendant_container", CreativePendantContainer::new);

	public static final CreativeModeTab CHANCE_TAB = Registry.register(
			BuiltInRegistries.CREATIVE_MODE_TAB,
			new ResourceLocation(CCubesCore.MODID, "tab"),
			CreativeModeTab.builder(Row.TOP, 0).icon(() -> new ItemStack(CCubesBlocks.CHANCE_CUBE))
				.title(Component.translatable("itemGroup.chancecubes"))
				.displayItems((displayContext, entries) -> {
					List<ItemStack> stacks = ENTRIES.stream()
							.filter(registryObject -> registryObject != CCubesBlocks.GIANT_CUBE.asItem())
							.map(reg -> new ItemStack(reg)).toList();
					entries.acceptAll(stacks);
				}).build());

	private static <T extends Item> T registerItems(String id, T item) {
		T i = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(CCubesCore.MODID, id), item);
		ENTRIES.add(i);
		return i;
	}

	private static <T extends AbstractContainerMenu, V extends AbstractContainerScreen<T>> MenuType<T> registerMenuType(String id, MenuSupplier<T> sup) {
		MenuType<T> menuType = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(CCubesCore.MODID, id), new MenuType<T>(sup, FeatureFlags.VANILLA_SET));
		// MenuScreens.register(CREATIVE_PENDANT_CONTAINER, CreativePendantScreen::new);
		return menuType;
	}

	public static void register() { }

}
