package com.github.mistlotrtweaks;

import com.github.mistlotrtweaks.config.MistLotrTweaksConfig;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(
    modid = MistLotrTweaks.MODID,
    name = MistLotrTweaks.NAME,
    version = MistLotrTweaks.VERSION,
    acceptableRemoteVersions = "*",
    dependencies = "required-after:gtnhmixins@[2.0.0,);required-after:lotr;"
)
public class MistLotrTweaks {

    public static final String MODID = "mistlotrtweaks";
    public static final String NAME = "Mist's Lotr tweaks";
    public static final String VERSION = "GRADLETOKEN_VERSION";
    public static final Logger logger = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        String configFolder = event.getModConfigurationDirectory().getAbsolutePath() + File.separator + MODID + File.separator;
        MistLotrTweaksConfig.loadConfig(new File(configFolder + "config.cfg"));
    }

}
