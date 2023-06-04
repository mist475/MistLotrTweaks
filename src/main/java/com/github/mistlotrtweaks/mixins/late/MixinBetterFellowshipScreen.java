package com.github.mistlotrtweaks.mixins.late;

import com.github.mistlotrtweaks.Util;
import lotr.client.gui.LOTRGuiFellowships;
import lotr.client.gui.LOTRGuiMenuBase;
import lotr.common.fellowship.LOTRFellowshipClient;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(value = LOTRGuiFellowships.class)
public abstract class MixinBetterFellowshipScreen extends LOTRGuiMenuBase {

    /**
     * Adds the icons from the per-fellowship screen to the main fellowship screen
     */
    @Inject(method = "drawFellowshipEntry(Llotr/common/fellowship/LOTRFellowshipClient;IIIIZI)V", at = @At("TAIL"), remap = false)
    private void renderPerFellowShipIcons(LOTRFellowshipClient fs, int x, int y, int mouseX, int mouseY, boolean isInvite, int selectWidth, CallbackInfo ci) {
        if (!isInvite) {
            //add icons a little to the right so the scrollbar doesn't get covered up
            x += 20;

            //Text and icons render at different heights relative to each-other
            //Due to size differences there will always be an extra pixel at the top or bottom
            y -= 4;

            //Get player UUID to check if the player is a guide (admin in code) of the fellowship
            //Note: doesn't work with offline mode servers, making this impossible to test in a dev environment
            UUID playerUUID = EntityPlayer.func_146094_a(Minecraft.getMinecraft().thePlayer.getGameProfile());

            //Has to match the size of the icon you want to load
            int iconSize = 16;

            //check condition, shift other icons accordingly
            //Render code itself adapted from LOTRGuiFellowships
            int adminX = xSize, mapLocX = xSize, preventPvpX = xSize, preventUnitFriendlyFireX = xSize;

            if (fs.isAdmin(playerUUID)) {
                Util.setUpFellowshipIconRender(this.mc);
                this.drawTexturedModalRect(x + adminX, y + 4, 8, 0, 8, 8);
                mapLocX += 20;
                preventPvpX += 20;
                preventUnitFriendlyFireX += 20;
            }

            if (fs.getShowMapLocations()) {
                Util.setUpFellowshipIconRender(this.mc);
                this.drawTexturedModalRect(x + mapLocX, y, 96, 48, iconSize, iconSize);
                preventPvpX += 20;
                preventUnitFriendlyFireX += 20;
            }

            if (!fs.getPreventPVP()) {
                Util.setUpFellowshipIconRender(this.mc);
                this.drawTexturedModalRect(x + preventPvpX, y, 64, 48, iconSize, iconSize);
                preventUnitFriendlyFireX += 20;
            }

            if (!fs.getPreventHiredFriendlyFire()) {
                Util.setUpFellowshipIconRender(this.mc);
                this.drawTexturedModalRect(x + preventUnitFriendlyFireX, y, 80, 48, iconSize, iconSize);
            }
        }
    }
}
