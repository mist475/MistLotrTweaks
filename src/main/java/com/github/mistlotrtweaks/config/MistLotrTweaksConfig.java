package com.github.mistlotrtweaks.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Config class, adapted from BugTorch
 */
public class MistLotrTweaksConfig {

    public static boolean enableBetterFellowshipScreen;

    public static boolean enableReforgeAid;

    //Category names
    static final String categoryProductivity = "Productivity";

    /**
     * Client side changes
     */
    public static void loadClientConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        enableBetterFellowshipScreen = config.getBoolean("betterFellowshipScreen", categoryProductivity, true, "Adds fellowship permission icons to the fellowship list (shows if map loc is enabled, if you are guide, if pvp is enabled and if unit friendly fire is enabled)");

        enableReforgeAid = config.getBoolean("reforgeAid", categoryProductivity, true, "Shows the modifiers of the item you're reforging without having to hover over the item");

        if (config.hasChanged()) {
            config.save();
        }
    }

}
