package chanceCubes.modifier;

import chanceCubes.CCubesCore;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;

public record AddCCubesFeaturesBiomeModifier(int id, HolderSet<Biome> biomes, HolderSet<PlacedFeature> features, Decoration step) implements BiomeModifier
{
	@Override
	public void modify(Holder<Biome> biome, Phase phase, Builder builder)
	{
		boolean flag = id == 0 ? CCubesCore.CONFIG.get().surfaceGeneration : CCubesCore.CONFIG.get().oreGeneration;
		if (phase == Phase.ADD && this.biomes.contains(biome) && flag)
		{
			BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
			this.features.forEach(holder -> generationSettings.addFeature(this.step, holder));
		}
	}

	@Override
	public Codec<? extends BiomeModifier> codec()
	{
		return CCubesModifiers.ADD_CCUBES_FEATURES_BIOME_MODIFIER_TYPE;
	}
}