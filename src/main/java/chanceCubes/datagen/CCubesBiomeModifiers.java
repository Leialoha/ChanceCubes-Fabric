package chanceCubes.datagen;

import chanceCubes.CCubesCore;
import chanceCubes.listeners.WorldGen;
import chanceCubes.modifier.AddCCubesFeaturesBiomeModifier;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraft.core.registries.BuiltInRegistries;

public class CCubesBiomeModifiers
{
	public static ResourceKey<BiomeModification> CC_SURFACE = createKey("chance_cube_worldgen");
	public static ResourceKey<BiomeModification> CC_ORE = createKey("chance_cube_oregen");

	private static ResourceKey<BiomeModification> createKey(String id) {
		BiomeModification bm = BiomeModifications.create(new ResourceLocation(CCubesCore.MODID, id));
		// return ResourceKey.create(BiomeModifications.create());
	}

	public static void bootstrap(BootstapContext<BiomeModification> context)
	{
		HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
		HolderGetter<PlacedFeature> placedGetter = context.lookup(Registries.PLACED_FEATURE);

		context.register(CC_SURFACE, new AddCCubesFeaturesBiomeModifier(
				0,
				biomeGetter.getOrThrow(BiomeTags.IS_OVERWORLD),
				HolderSet.direct(placedGetter.getOrThrow(WorldGen.CC_SURFACE)),
				GenerationStep.Decoration.TOP_LAYER_MODIFICATION));

		context.register(CC_ORE, new AddCCubesFeaturesBiomeModifier(
				1,
				biomeGetter.getOrThrow(BiomeTags.IS_OVERWORLD),
				HolderSet.direct(placedGetter.getOrThrow(WorldGen.CC_ORE)),
				GenerationStep.Decoration.UNDERGROUND_ORES));
	}
}
