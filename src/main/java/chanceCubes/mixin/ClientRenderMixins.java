package chanceCubes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import chanceCubes.client.ClientHelper;
import me.shedaniel.errornotifier.launch.render.math.Matrix4f;
import net.minecraft.client.Camera;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;

public abstract class ClientRenderMixins {

    @Mixin(Gui.class)
    public abstract class GuiRenderOverlay {

        @Shadow
        private int screenWidth;

        @Shadow
        private int screenHeight;

        @Inject(method = "render", at = @At("TAIL"))
        private void render(GuiGraphics guiGraphics, float delta, CallbackInfo info) {
            Gui that = (Gui) (Object) this;
            ClientHelper.guiRenderEvent.onGuiRender(that, guiGraphics, screenWidth, screenHeight, delta);
        }
    }

    @Mixin(LevelRenderer.class)
    public abstract class ClientLevelRenderer {

        @Inject(method = "renderLevel", at = @At("TAIL"))
        private void render(PoseStack poseStack, float f, long l, boolean bl, Camera camera, GameRenderer gameRenderer,
                LightTexture lightTexture, Matrix4f matrix4f, CallbackInfo info) {
            ClientHelper.levelRenderEvent.onLevelRender(poseStack, camera);
        }
    }

}
