package chanceCubes.config;

import chanceCubes.CCubesCore;
import chanceCubes.rewards.IChanceCubeReward;
import chanceCubes.util.NonreplaceableBlockOverride;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

@Config(name = CCubesCore.MODID)
public class CCubesSettings implements ConfigData
{
	@Comment("Number of uses for a pendant")
	public int pendantUses = 32;
	@Comment("Set to true if the default rewards should be loaded, false if they shouldn't")
	public boolean enableHardCodedRewards = true;
	@Comment("Set to true Giant Chance Cubes should be disabled")
	public boolean  disableGiantCC = false;

	@Comment("The minimum chance range value. Changes the range of chance that the chance block can pick from. i.e. If you have your rangemin set to 10 and range max set to 15. A chance cube with a chance value of 0 can get rewards of -10 to 15 in chance value.")
	public int rangeMin = 10;
	@Comment("The maximum chance range value. Changes the range of chance that the chance block can pick from. i.e. If you have your rangemin set to 10 and range max set to 15. A chance cube with a chance value of 0 can get rewards of -10 to 15 in chance value.")
	public int rangeMax = 10;
	@Comment("Set to true if the D20's should have any chance value from -100 to 100. Set to false to have the D20's only have a chance value of either -100 or 100")
	public boolean d20UseNormalChances = false;
	@Comment("Set to true if the mod should ignore chance values and give each reward and equal chance to be picked")
	public boolean rewardsEqualChance = false;

	@Comment("True if Chance Cubes should generate like ores with in the world. false if they should not")
	public boolean oreGeneration = true;
	@Comment("true if Chance Cubes should generate on the surface of the world. false if they should not")
	public boolean surfaceGeneration = true;
	@Comment("Chance of a chunk to have a chance cube spawned on the surface. The math is 1/(surfaceGenerationAmount), so increase to make more rare, and decrease to make more common.")
	public int surfaceGenAmount = 100;
	@Comment("Worlds that Chance cubes shold not generate in")
	public List<String> blockedWorlds = new ArrayList<>();
	@Comment("True if Chance Cubes should generate as chest loot in the world. false if they should not")
	public boolean chestLoot = true;

	@Comment("True if Chance Cubes should load in user specific rewards (for a select few only)")
	public boolean userSpecificRewards = true;
	@Comment("True if Chance Cubes should check for globally disabled rewards (Rewards that are usually bugged or not working correctly). NOTE: The mod sends your Chance Cubes mod version to the web server to check for disabled rewards for your given version and the version number is subsequently logged. Feel free to make an inquiry if you wish to know more.")
	public boolean disabledRewards = true;

	@Comment("Set to false if you wish to disable the super special holiday rewards. Why would you want to do that?")
	public boolean holidayRewards = true;
	@Comment("Don't touch! Well I mean you can touch it, if you want. I can't stop you. I'm only text.")
	public boolean holidayRewardTriggered = false;
	public static boolean doesHolidayRewardTrigger = false;
	public static IChanceCubeReward holidayReward = null;
	public static boolean hasHolidayTexture = false;
	public static String holidayTextureName = "";

	@Comment("How many blocks above the Chance Cube that a block that will fall should be dropped from")
	public int dropHeight = 20;

	public static final List<BlockState> nonReplaceableBlocksIMC = new ArrayList<>();
	public static List<BlockState> nonReplaceableBlocks = new ArrayList<>();
	public static final List<NonreplaceableBlockOverride> nonReplaceableBlocksOverrides = new ArrayList<>();
	public static final List<BlockState> backupNRB = new ArrayList<>();

	@Comment("Blocks that should not be replaced when rewards are \"restored\" after a reward is over, i.e don't remove graves when the boss dome get's cleared")
	public List<String> blockRestoreBlacklist = new ArrayList<>();

	public static boolean testRewards;
	public static boolean testCustomRewards;
	public static int testingRewardIndex = 0;

	public boolean isBlockedWorld(String world)
	{
		for(String blockedWorld : blockedWorlds)
			if(blockedWorld.equalsIgnoreCase(world))
				return true;
		return false;
	}
}