package com.github.mistlotrtweaks.util;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Field;

import static lotr.client.gui.LOTRGuiFellowships.iconsTextures;

/**
 * Static util functions to reduce mixin complexity
 */
public class Util {
    public static void setUpFellowshipIconRender(Minecraft mc) {
        mc.getTextureManager().bindTexture(iconsTextures);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * @author eigenraven
     * <br>
     * Taken with permission from <a href="https://github.com/GTNewHorizons/Hodgepodge/blob/master/src/main/java/com/mitchej123/hodgepodge/mixins/late/biomesoplenty/MixinBOPReflectionHelper.java">...</a>
     */
    @SuppressWarnings({"rawtypes","unchecked"})
    public static <T, E> void setPrivateFinalValue(Class<? super T> classToAccess, T instance, E value,
                                                   String... fieldNames) {
        final Field field = ReflectionHelper.findField(
            classToAccess,
            ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), fieldNames));
        try {
            final Fields.ClassFields.Field accessor = Fields.ofClass(classToAccess)
                .getUntypedField(Fields.LookupType.DECLARED_IN_HIERARCHY, field.getName());
            accessor.setValue(instance, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
