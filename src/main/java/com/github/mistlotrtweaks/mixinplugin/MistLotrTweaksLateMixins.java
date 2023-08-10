package com.github.mistlotrtweaks.mixinplugin;

import com.github.mistlotrtweaks.MistLotrTweaks;
import com.github.mistlotrtweaks.config.MistLotrTweaksConfig;
import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import cpw.mods.fml.relauncher.FMLLaunchHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Mixin loader, adapted from BugTorch
 */
@LateMixin
public class MistLotrTweaksLateMixins implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.mistlotrtweaks.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        if (!loadedMods.contains("lotr")) {
            MistLotrTweaksConfig.enableBetterFellowshipScreen = false;
            MistLotrTweaksConfig.enableReforgeAid = false;
            MistLotrTweaksConfig.enableFixJava12Compat = false;
            MistLotrTweaksConfig.enableWeather2LOTRCompat = false;
        }

        MistLotrTweaks.logger.info("Kicking off Mist's Lotr Tweaks late mixins.");
        boolean client = FMLLaunchHandler.side().isClient();

        List<String> mixins = new ArrayList<>();

        if (loadedMods.contains("lotr")) {
            if (client) {
                if (MistLotrTweaksConfig.enableWeather2LOTRCompat && loadedMods.contains("weather2")) {
                    mixins.add("MixinWeather2GuiConfigEditor");
                    mixins.add("MixinWeather2ClientTickHandler");
                }
                if (MistLotrTweaksConfig.enableBetterFellowshipScreen) {
                    mixins.add("MixinBetterFellowshipScreen");
                }
                if (MistLotrTweaksConfig.enableReforgeAid) {
                    mixins.add("MixinReforgeAid");
                }
            }
            //Hodgepodge 2.2.23+ contains the same patch
            if (MistLotrTweaksConfig.enableFixJava12Compat && !loadedMods.contains("hodgepodge")) {
                mixins.add("MixinLOTRLogReflection");
                mixins.add("MixinRedirectHuornAI");
                mixins.add("MixinRemoveUnlockFinalField");
            }
            if (MistLotrTweaksConfig.addRedstoneToMiddleEarth == 1) {
                mixins.add("MixinLOTRBiomeGenRedMountains");
            }
            else if (MistLotrTweaksConfig.addRedstoneToMiddleEarth == 2) {
                mixins.add("MixinLOTRBiome");
            }
        }
        return mixins;
    }

}
