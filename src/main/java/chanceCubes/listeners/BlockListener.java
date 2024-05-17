package chanceCubes.listeners;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import chanceCubes.client.listeners.RenderEvent;
import chanceCubes.util.RewardsUtil;
import chanceCubes.util.Scheduler;
import chanceCubes.util.SchematicUtil;
import chanceCubes.util.Task;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;

public class BlockListener {
	private boolean setDelay = false;

	public void onBlockBreak(Player player, BlockPos blockPos, CallbackInfo info) {
		if (this.setSchematicPoint(1, player, blockPos))
			info.cancel();
	}

	public void onBlockInteract(UseOnContext useOnContext, CallbackInfo info) {
		if (this.setSchematicPoint(2, useOnContext.getPlayer(), useOnContext.getClickedPos()))
			info.cancel();
	}

	public void onAirInteractRight(Player player, InteractionHand interactionHand, BlockPos blockPos) {
		this.setSchematicPoint(2, player, blockPos);
	}

	public void onAirInteractLeft(Player player, InteractionHand interactionHand, BlockPos blockPos) {
		this.setSchematicPoint(1, player, blockPos);
	}

	public boolean setSchematicPoint(int point, Player player, BlockPos pos) {
		if (Minecraft.getInstance().isLocalServer() && RenderEvent.isCreatingSchematic() && !setDelay) {
			if (player.isCreative()) {
				boolean flag = false;
				if (point == 1) {
					SchematicUtil.selectionPoints[0] = pos;
					RewardsUtil.sendMessageToPlayer(player, "Point 1 set");
					flag = true;
				} else if (point == 2) {
					SchematicUtil.selectionPoints[1] = pos;
					RewardsUtil.sendMessageToPlayer(player, "Point 2 set");
					flag = true;
				}

				if (flag) {
					setDelay = true;
					Scheduler.scheduleTask(new Task("Schematic_Point_Set_Delay", 10) {

						@Override
						public void callback() {
							setDelay = false;
						}

					});
					return true;
				}
			}
		}
		return false;
	}
}