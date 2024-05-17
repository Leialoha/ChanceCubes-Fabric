package chanceCubes.client.listeners;

import chanceCubes.util.SchematicUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;

public class RenderEvent {
	private static boolean islookingAt = false;
	private static boolean creatingSchematic = false;
	private static int chance = -201;
	private static int chanceIncrease = 0;

	@Environment(EnvType.CLIENT)
	public void onGuiRender(Gui gui, GuiGraphics guiGraphics, int width, int height, float delta) {
		// if (event.getOverlay().id() != VanillaGuiOverlay.TITLE_TEXT.id() || event.isCancelable())
		// 	return;

		// int width = event.getWindow().getGuiScaledWidth();
		// int height = event.getWindow().getGuiScaledHeight();

		Font fontRenderer = Minecraft.getInstance().font;
		PoseStack matrixStack = guiGraphics.pose();

		if (islookingAt) {
			matrixStack.pushPose();
			// TODO
			// GlStateManager.disableLighting();
			// GlStateManager.color4f(1F, 1F, 1F, 1F);
			if (chance == -201) {
				guiGraphics.drawString(fontRenderer, "The chance of this cube is: Destruction... Probably",
						(int) (width / 2f) - 80, (int) (height / 2f) - 30, 16777215, false);
			} else {
				guiGraphics.drawString(fontRenderer, "The chance of this cube is: " + chance, (int) (width / 2f) - 80,
						(int) (height / 2f) - 30, 16777215, false);
				if (chanceIncrease != 0) {
					int c = chance + chanceIncrease;
					guiGraphics.drawString(fontRenderer, "Chance with pendants is: " + Math.min(100, Math.max(c, -100)),
							(int) (width / 2f) - 80, (int) (height / 2f) - 15, 16777215, false);
				}
			}
			// TODO
			// GlStateManager.enableLighting();
			matrixStack.popPose();
		}
		if (creatingSchematic) {
			matrixStack.pushPose();
			// TODO
			// GlStateManager.disableLighting();
			// GlStateManager.color4f(1F, 1F, 1F, 1F);

			String text1 = "--- Creating A Chance Cube Schematic ---";
			String text2 = "Right or left click a block or air to set positions.";
			String text3 = "/ChanceCubes schematic create to continue";
			String text4 = "/ChanceCubes schematic cancel to cancel";
			String text5 = "Point 1";
			String text6 = "Point 2";

			guiGraphics.drawString(fontRenderer, text1, 
					(int) ((width / 2f) - (fontRenderer.width(text1) / 2f)), 10, 0xFFFFFF,
					false);
			guiGraphics.drawString(fontRenderer, text2, 
					(int) ((width / 2f) - (fontRenderer.width(text2) / 2f)), 20, 0xFFFFFF,
					false);
			guiGraphics.drawString(fontRenderer, text3, 
					(int) ((width / 2f) - (fontRenderer.width(text3) / 2f)), 30, 0xFFFFFF,
					false);
			guiGraphics.drawString(fontRenderer, text4, 
					(int) ((width / 2f) - (fontRenderer.width(text4) / 2f)), 40, 0xFFFFFF,
					false);
			guiGraphics.drawString(fontRenderer, text5, 
					(int) ((width / 2f) - (fontRenderer.width(text5) / 2f)), 60,
					SchematicUtil.selectionPoints[0] == null ? 0xFF0000 : 0x00FF00, false);
			guiGraphics.drawString(fontRenderer, text6, 
					(int) ((width / 2f) - (fontRenderer.width(text6) / 2f)), 70,
					SchematicUtil.selectionPoints[1] == null ? 0xFF0000 : 0x00FF00, false);

			// TODO
			// GlStateManager.enableLighting();
			matrixStack.popPose();
		}
	}

	public static void setLookingAtChance(int c) {
		chance = c;
	}

	public static void setLookingAt(boolean lookingAt) {
		islookingAt = lookingAt;
	}

	public static void setChanceIncrease(int increase) {
		chanceIncrease = increase;
	}

	public static void setCreatingSchematic(boolean isCreating) {
		creatingSchematic = isCreating;
	}

	public static boolean isCreatingSchematic() {
		return creatingSchematic;
	}
}