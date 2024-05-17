package chanceCubes.blocks;

import chanceCubes.CCubesCore;
import chanceCubes.tileentities.TileChanceCube;
import chanceCubes.tileentities.TileChanceD20;
import chanceCubes.tileentities.TileCubeDispenser;
import chanceCubes.tileentities.TileGiantCube;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;

public class CCubesBlocks
{
	public static final BaseChanceBlock CHANCE_CUBE = registerBlock("chance_cube", new BlockChanceCube());
	public static final BaseChanceBlock CHANCE_ICOSAHEDRON = registerBlock("chance_icosahedron", new BlockChanceD20());
	public static final BaseChanceBlock GIANT_CUBE = registerBlock("giant_chance_cube", new BlockGiantCube());
	public static final BaseChanceBlock COMPACT_GIANT_CUBE = registerBlock("compact_giant_chance_cube", new BlockCompactGiantCube());
	public static final BaseChanceBlock CUBE_DISPENSER = registerBlock("cube_dispenser", new BlockCubeDispenser());

	public static final BlockEntityType<TileChanceCube> TILE_CHANCE_CUBE = registerBlockEntity("tile_chance_cube", TileChanceCube::new, CCubesBlocks.CHANCE_CUBE);
	public static final BlockEntityType<TileChanceD20> TILE_CHANCE_ICOSAHEDRON = registerBlockEntity("tile_chance_icosahedron", TileChanceD20::new, CCubesBlocks.CHANCE_ICOSAHEDRON);
	public static final BlockEntityType<TileGiantCube> TILE_CHANCE_GIANT = registerBlockEntity("tile_chance_giant", TileGiantCube::new, CCubesBlocks.GIANT_CUBE);
	public static final BlockEntityType<TileCubeDispenser> TILE_CUBE_DISPENSER = registerBlockEntity("tile_cube_dispenser", TileCubeDispenser::new, CCubesBlocks.CUBE_DISPENSER);
	
	private static <T extends Block> T registerBlock(String id, T block) {
		return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(CCubesCore.MODID, id), block);
	}

	private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String id, BlockEntitySupplier<T> sup, Block ...blocks) {
		return Registry.register( BuiltInRegistries.BLOCK_ENTITY_TYPE, id, BlockEntityType.Builder.of(sup, blocks).build(null));
	}

	public static void register() { }
}