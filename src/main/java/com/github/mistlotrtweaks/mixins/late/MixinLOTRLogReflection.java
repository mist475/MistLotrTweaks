package com.github.mistlotrtweaks.mixins.late;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import lotr.common.util.LOTRLog;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Field;

@Mixin(LOTRLog.class)
public class MixinLOTRLogReflection {
    @Shadow(remap = false)
    public static Logger logger;

    /**
     * @author Mist475
     * @reason Replace call to unlock final field with simple reflection
     */
    @Overwrite(remap = false)
    public static void findLogger() {
        try {
            final Field field = ReflectionHelper.findField(
                MinecraftServer.class,
                ObfuscationReflectionHelper.remapFieldNames(MinecraftServer.class.getName(), "field_147145_h", "logger"));
            logger = (Logger) field.get(null);

        }
        catch (final Exception e) {
            FMLLog.warning("LOTR: Failed to find logger!");
            e.printStackTrace();
        }
    }
}