package com.github.mistlotrtweaks.mixins.late;

import cpw.mods.fml.relauncher.ReflectionHelper;
import modconfig.ModConfigData;
import modconfig.gui.GuiConfigEditor;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiConfigEditor.class,remap = false)
public abstract class MixinWeather2GuiConfigEditor extends GuiScreen {

    /**
     * Ensure the proxied values added in MixinEntityRenderer are updated when weather2 config gets saved
     */
    @Inject(method = "updateChangedValues()V", at = @At(value = "HEAD", remap = false), remap = false)
    private void updateProxiedFields(CallbackInfo ci) {
        this.getData()
            .configData
            .stream()
            .filter(configEntryInfo -> configEntryInfo.name.equals("Misc_proxyRenderOverrideEnabled") ||
                    configEntryInfo.name.equals("Particle_RainSnow"))
            .forEach(configEntryInfo -> {
                boolean value = Boolean.parseBoolean(configEntryInfo.editBox.text);
                String fieldName = configEntryInfo.name.equals("Misc_proxyRenderOverrideEnabled") ? "Misc_proxyRenderOverrideEnabled" : "Particle_RainSnow";
                ReflectionHelper.setPrivateValue(EntityRenderer.class, null, value, fieldName);
            });
    }

    @Shadow(remap = false)
    public abstract ModConfigData getData();
}
