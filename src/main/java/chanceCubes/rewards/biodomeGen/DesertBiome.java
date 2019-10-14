package chanceCubes.rewards.biodomeGen;

import chanceCubes.rewards.rewardparts.OffsetBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class DesertBiome extends BaseBiome
{
	public DesertBiome(String name)
	{
		super(name);
	}

	@Override
	public Block getFloorBlock()
	{
		return Blocks.SANDSTONE;
	}

	public void getRandomGenBlock(float dist, Random rand, int x, int y, int z, List<OffsetBlock> blocks, int delay)
	{
		if(y != 0)
			return;

		if(dist < 0 && rand.nextInt(50) == 0)
		{
			delay++;
			OffsetBlock osb = new OffsetBlock(x, y, z, Blocks.SAND, false, (delay / BioDomeGen.delayShorten) + 1);
			blocks.add(osb);
			osb = new OffsetBlock(x, y - 1, z, Blocks.SANDSTONE, false, (delay / BioDomeGen.delayShorten));
			blocks.add(osb);
			delay++;
			osb = new OffsetBlock(x, y + 1, z, Blocks.DEADBUSH, false, (delay / BioDomeGen.delayShorten));
			blocks.add(osb);
			delay++;
		}

		if(dist < 0 && rand.nextInt(60) == 0)
		{
			delay++;
			OffsetBlock osb = new OffsetBlock(x, y, z, Blocks.SAND, false, (delay / BioDomeGen.delayShorten) + 1);
			blocks.add(osb);
			osb = new OffsetBlock(x, y - 1, z, Blocks.SANDSTONE, false, (delay / BioDomeGen.delayShorten));
			blocks.add(osb);
			delay++;
			osb = new OffsetBlock(x, y + 1, z, Blocks.CACTUS, false, (delay / BioDomeGen.delayShorten));
			blocks.add(osb);
			delay++;
			osb = new OffsetBlock(x, y + 2, z, Blocks.CACTUS, false, (delay / BioDomeGen.delayShorten));
			blocks.add(osb);
			delay++;
			osb = new OffsetBlock(x, y + 3, z, Blocks.CACTUS, false, (delay / BioDomeGen.delayShorten));
			blocks.add(osb);
		}
	}

	@Override
	public void spawnEntities(BlockPos center, World world)
	{

	}
}