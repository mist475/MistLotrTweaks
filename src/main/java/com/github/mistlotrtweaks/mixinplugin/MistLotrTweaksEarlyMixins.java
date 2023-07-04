package com.github.mistlotrtweaks.mixinplugin;

import com.github.mistlotrtweaks.MistLotrTweaks;
import com.github.mistlotrtweaks.config.MistLotrTweaksConfig;
import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Adapted from BugTorch
 */
@IFMLLoadingPlugin.Name("MistLotrTweaksEarlyMixins")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class MistLotrTweaksEarlyMixins implements IFMLLoadingPlugin, IEarlyMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.mistlotrtweaks.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        String configFolder =  "config" + File.separator + MistLotrTweaks.MODID + File.separator;
        MistLotrTweaksConfig.loadConfig(new File(Launch.minecraftHome, configFolder + "config.cfg"));

        MistLotrTweaks.logger.info("Kicking off Mist's Lotr tweaks early mixins.");
        boolean client = FMLLaunchHandler.side().isClient();
        List<String> mixins = new ArrayList<>();

        return mixins;
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
