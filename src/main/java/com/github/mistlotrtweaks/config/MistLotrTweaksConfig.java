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

    public static boolean enableWeather2LOTRCompat;
    public static int addRedstoneToMiddleEarth;

    public static int redstoneVeinSize;
    public static int redstoneMinYLevel;
    public static int redstoneMaxYLevel;
    public static float redstoneRarity;

    //Category names
    static final String categoryProductivity = "Productivity";

    static final String categoryBugFixes = "BugFixes";

    static final String categoryModdedCompatability = "Features that make working with other mods easier";

    /**
     * General config
     */
    public static void loadConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        enableBetterFellowshipScreen = config.getBoolean("betterFellowshipScreen", categoryProductivity, true, "Adds fellowship permission icons to the fellowship list (shows if map loc is enabled, if you are guide, if pvp is enabled and if unit friendly fire is enabled)");

        enableReforgeAid = config.getBoolean("reforgeAid", categoryProductivity, true, "Shows the modifiers of the item you're reforging without having to hover over the item");

        enableFixJava12Compat = config.getBoolean("fixJava12Compat", categoryBugFixes, true, "Fix java 12+ compatability");

        enableWeather2LOTRCompat = config.getBoolean("weather2Compat", categoryModdedCompatability, true, "Fix clash between weather2 and LOTRs EntityRenderer. This fixes heavy lag when both are installed. This does NOT fix any other incompatibilities that might be present");

        addRedstoneToMiddleEarth = config.getInt("redstoneInME", categoryModdedCompatability, 1, 0, 2, "Add redstone ore generation to middle earth, 0 = off, 1 = only in red mountains, 2 = everywhere");
        redstoneVeinSize = config.getInt("redstoneVeinSize", categoryModdedCompatability, 8, 0, 64, "Redstone ore vein size");
        redstoneMinYLevel = config.getInt("redstoneMinYLevel", categoryModdedCompatability, 0, 0, 256, "Redstone ore minimum y level");
        redstoneMaxYLevel = config.getInt("redstoneMaxYLevel", categoryModdedCompatability, 48, 0, 256, "Redstone ore maximum y level");
        redstoneRarity = config.getFloat("redstoneRarity", categoryModdedCompatability, 8, 0, 100, "Redstone ore rarity");

        if (config.hasChanged()) {
            config.save();
        }
    }

}
