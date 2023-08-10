package com.github.mistlotrtweaks.mixins.early;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer implements IResourceManagerReloadListener {
    @Unique
    private static boolean Misc_proxyRenderOverrideEnabled = false;

    @Unique
    private static boolean Particle_RainSnow = false;

    @Unique
    private static boolean mistLotrTweaks$weather2PresentCheckComplete = false;

    @Unique
    private static boolean mistLotrTweaks$weather2Present = false;

    /**
     * Mixin version of EntityRendererProxyWeather2Mini#renderRainSnow.
     * Uses reflection as detecting if weather2 is installed is impossible due this being an early mixin
     * In game setting adjustment breaks with this, I fix this in MixinWeather2GuiConfigEditor
     */
    @Inject(method = "renderRainSnow(F)V", at = @At("HEAD"), cancellable = true)
    private void fixWeather2EntityRendererRenderRainSnow(float p_78474_1_, CallbackInfo ci) {
        if (!mistLotrTweaks$weather2PresentCheckComplete) {
            mistLotrTweaks$weather2Present = Loader.isModLoaded("weather2");
            if (mistLotrTweaks$weather2Present) {
                try {
                    Class<?> MiscConfig = Class.forName("weather2.config.ConfigMisc");
                    Misc_proxyRenderOverrideEnabled = ReflectionHelper.getPrivateValue(MiscConfig,null,"Misc_proxyRenderOverrideEnabled");
                    Particle_RainSnow = ReflectionHelper.getPrivateValue(MiscConfig,null,"Particle_RainSnow");
                }
                catch (ClassNotFoundException ignored) {

                }
            }
            mistLotrTweaks$weather2PresentCheckComplete = true;
        }
        if (mistLotrTweaks$weather2Present) {
            if (Misc_proxyRenderOverrideEnabled || Particle_RainSnow) {
                ci.cancel();
            }
        }
    }
}
