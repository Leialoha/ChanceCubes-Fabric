package chanceCubes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import chanceCubes.client.ClientHelper;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ClientInteractMixins {

    @Mixin(Block.class)
    public abstract class ClientInteractionBlock {

        @Inject(method = "playerWillDestroy", at = @At("HEAD"), cancellable = true)
        private void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player, CallbackInfo info) {
            ClientHelper.blockListener.onBlockBreak(player, blockPos, info);
        }
    }

    @Mixin(Item.class)
    public abstract class ClientInteractionItem {

        @Inject(method = "use", at = @At("HEAD"))
        private void use(Level level, Player player, InteractionHand interactionHand, CallbackInfo info) {
            ClientHelper.blockListener.onAirInteractRight(player, interactionHand, player.blockPosition());
        }

        @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
        private void useOn(UseOnContext useOnContext, CallbackInfo info) {
            ClientHelper.blockListener.onBlockInteract(useOnContext, info);
        }
    }

    @Mixin(LocalPlayer.class)
    public abstract class ClientInteractionLocalPlayer {

        @Inject(method = "swing", at = @At("TAIL"))
        private void swing(InteractionHand interactionHand, CallbackInfo info) {
            LocalPlayer player = (LocalPlayer) (Object) this;
            ClientHelper.blockListener.onAirInteractLeft(player, interactionHand, player.blockPosition());
        }
    }

}
