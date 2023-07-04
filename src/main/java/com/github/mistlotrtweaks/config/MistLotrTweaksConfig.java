package com.github.mistlotrtweaks.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Config class, adapted from BugTorch
 */
public class MistLotrTweaksConfig {

    public static boolean enableBetterFellowshipScreen;

    public static boolean enableReforgeAid;

    public static boolean enableFixJava12Compat;

    //Category names
    static final String categoryProductivity = "Productivity";

    static final String categoryBugFixes = "BugFixes";

    /**
     * Client side changes
     */
    public static void loadConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        enableBetterFellowshipScreen = config.getBoolean("betterFellowshipScreen", categoryProductivity, true, "Adds fellowship permission icons to the fellowship list (shows if map loc is enabled, if you are guide, if pvp is enabled and if unit friendly fire is enabled)");

        enableReforgeAid = config.getBoolean("reforgeAid", categoryProductivity, true, "Shows the modifiers of the item you're reforging without having to hover over the item");

        enableFixJava12Compat = config.getBoolean("fixJava12Compat", categoryBugFixes, true, "Fix java 12+ compatability");

        if (config.hasChanged()) {
            config.save();
        }
    }

}
