package chanceCubes.modifier;

import chanceCubes.CCubesCore;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;

public class CCubesModifiers
{
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.BIOME_MODIFIER_SERIALIZERS, CCubesCore.MODID);

	public static final Codec<AddCCubesFeaturesBiomeModifier> ADD_CCUBES_FEATURES_BIOME_MODIFIER_TYPE = Registry.register(BuiltInRegistries.BIOME_SOURCE, new ResourceLocation(CCubesCore.MODID, "add_chancecubes_features"),
			RecordCodecBuilder.create(builder -> builder.group(
					Codec.intRange(0, 1).fieldOf("id").forGetter(AddCCubesFeaturesBiomeModifier::id),
					Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddCCubesFeaturesBiomeModifier::biomes),
					PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(AddCCubesFeaturesBiomeModifier::features),
					GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(AddCCubesFeaturesBiomeModifier::step)
			).apply(builder, AddCCubesFeaturesBiomeModifier::new))
	);
}
