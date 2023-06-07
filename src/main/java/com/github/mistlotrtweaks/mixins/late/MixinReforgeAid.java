package com.github.mistlotrtweaks.mixins.late;

import lotr.client.gui.LOTRGuiAnvil;
import lotr.common.inventory.LOTRContainerAnvil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LOTRGuiAnvil.class)
public abstract class MixinReforgeAid extends GuiContainer {
    @Shadow(remap = false)
    private LOTRContainerAnvil theAnvil;

    public MixinReforgeAid(Container p_i1072_1_) {
        super(p_i1072_1_);
    }

    /**
     * Displays the modifiers of the item reforged item,
     * as having to constantly click reforge and move the cursor to check the modifiers is tedious
     */
    @Inject(method = "drawScreen(IIF)V", at = @At("TAIL"))
    public void drawReforgeItemInfo(int i, int j, float f, CallbackInfo ci) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        //Magic numbers, gotta love 'em
        int yPos = (sr.getScaledHeight() / 2) - 82;
        int xPos = (sr.getScaledWidth() / 2) + 80;
        //Get item in input slot
        ItemStack reforgedItem = theAnvil.invInput.getStackInSlot(0);
        if (reforgedItem != null) {
            int availableWidth = (sr.getScaledWidth() - this.xSize) / 2;
            int itemLength = this.fontRendererObj.getStringWidth(reforgedItem.getDisplayName());
            //If there's not enough space, draw the item tooltip slightly over the gui
            if (availableWidth <= itemLength) {
                xPos = sr.getScaledWidth() - (itemLength + 15);
            }
            this.renderToolTip(reforgedItem, xPos, yPos);
        }
    }
}
