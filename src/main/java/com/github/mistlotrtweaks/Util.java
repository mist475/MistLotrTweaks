package com.github.mistlotrtweaks;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import static lotr.client.gui.LOTRGuiFellowships.iconsTextures;

/**
 * Static util functions to reduce mixin complexity
 */
public class Util {
    public static void setUpFellowshipIconRender(Minecraft mc) {
        mc.getTextureManager().bindTexture(iconsTextures);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
