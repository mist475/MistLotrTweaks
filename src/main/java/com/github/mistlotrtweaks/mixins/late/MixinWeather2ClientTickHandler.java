package com.github.mistlotrtweaks.mixins.late;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import weather2.ClientTickHandler;
import weather2.client.SceneEnhancer;
import weather2.client.gui.GuiEZConfig;
import weather2.config.ConfigMisc;
import weather2.util.WeatherUtilConfig;
import weather2.weathersystem.WeatherManagerClient;

@Mixin(value = ClientTickHandler.class, remap = false)
public abstract class MixinWeather2ClientTickHandler {

    @Shadow(remap = false)
    public static World lastWorld;
    @Shadow(remap = false)
    public static WeatherManagerClient weatherManager;
    @Shadow(remap = false)
    public static SceneEnhancer sceneEnhancer;

    /**
     * @author Mist475
     * @reason Remove instanceof check as subclass is replaced with mixin into main class
     */
    @Overwrite(remap = false)
    public void onTickInGame() {
        Minecraft mc = FMLClientHandler.instance().getClient();
        World world = mc.theWorld;

        if (world != null && world != lastWorld) {
            init(world);
        }

        if (world != null) {
            weatherManager.tick();
            if (ConfigMisc.Misc_ForceVanillaCloudsOff && world.provider.dimensionId == 0) {
                mc.gameSettings.clouds = false;
            }
        }

        if (world != null && WeatherUtilConfig.listDimensionsWindEffects.contains(world.provider.dimensionId)) {
            sceneEnhancer.tickClient();
        }

        if (world != null && mc.ingameGUI.getChatGUI().getSentMessages().size() > 0) {
            String msg = mc.ingameGUI.getChatGUI().getSentMessages().get(mc.ingameGUI.getChatGUI().getSentMessages().size() - 1);
            if (msg.equals("/weather2 config")) {
                mc.ingameGUI.getChatGUI().getSentMessages().remove(mc.ingameGUI.getChatGUI().getSentMessages().size() - 1);
                mc.displayGuiScreen(new GuiEZConfig());
            }
        }
    }

    @Shadow(remap = false)
    public static void init(World world) {

    }
}
