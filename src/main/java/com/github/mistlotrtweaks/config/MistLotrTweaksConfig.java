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

    public static boolean addDModFoxesToME;
    public static int baseFoxSpawnChance;
    public static int minimumFoxSpawnGroup;
    public static int maximumFoxSpawnGroup;

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

        addDModFoxesToME = config.getBoolean("addDModFoxesToME", categoryModdedCompatability, true, "Add DMod foxes to lotr biomes that have foxes in the 1.16.5 version of the mod");
        //Value taken from renewed 5.5
        //In renewed the weight for wolves is 10 * multiplier, in legacy it varies but is either 10 or 4 usually
        //As such I've taken the weight from renewed, halved it and then apply multiplier per biome
        baseFoxSpawnChance = config.getInt("baseFoxSpawnChance", categoryModdedCompatability, 8, 0, 100, "Base chance for foxes to spawn in lotr biomes that have foxes");

        minimumFoxSpawnGroup = config.getInt("minimumFoxSpawnGroup", categoryModdedCompatability, 2, 0, 100, "Minimum group size for fox spawns");
        maximumFoxSpawnGroup = config.getInt("maximumFoxSpawnGroup", categoryModdedCompatability, 4, 0, 100, "Maximum group size for fox spawns");
        if (config.hasChanged()) {
            config.save();
        }
    }

}
