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
        }

        MistLotrTweaks.logger.info("Kicking off Mist's Lotr Tweaks late mixins.");
        boolean client = FMLLaunchHandler.side().isClient();

        List<String> mixins = new ArrayList<>();
        if (client) {
            if (MistLotrTweaksConfig.enableBetterFellowshipScreen) {
                mixins.add("MixinBetterFellowshipScreen");
            }
            if (MistLotrTweaksConfig.enableReforgeAid) {
                mixins.add("MixinReforgeAid");
            }
        }

        return mixins;
    }

}
