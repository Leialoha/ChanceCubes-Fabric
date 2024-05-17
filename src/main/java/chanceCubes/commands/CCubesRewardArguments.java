package chanceCubes.commands;

import chanceCubes.CCubesCore;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class CCubesRewardArguments {
	public static final SingletonArgumentInfo<RewardArgument> REWARD_ARGUMENT_TYPE = Registry
			.register(BuiltInRegistries.COMMAND_ARGUMENT_TYPE, new ResourceLocation(CCubesCore.MODID, "reward"),
					SingletonArgumentInfo.contextFree(RewardArgument::rewardArgument));
}
