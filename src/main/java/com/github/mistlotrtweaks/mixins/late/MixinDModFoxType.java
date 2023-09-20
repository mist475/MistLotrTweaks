package com.github.mistlotrtweaks.mixins.late;

import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeGenForodwaith;
import lotr.common.world.biome.LOTRBiomeGenTaiga;
import lotr.common.world.biome.LOTRBiomeGenTundra;
import makamys.dmod.entity.EntityFox;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

@Mixin(value = EntityFox.Type.class, remap = false)
public abstract class MixinDModFoxType {
    @Unique
    private final static Random mistLotrTweaks$random = new Random();

    /**
     * @author Mist475
     * @reason Lotr doesn't register its biomes using the BiomeDictionary, in the future I might register them myself but this will do in the meantime
     */
    @Overwrite(remap = false)
    public static EntityFox.Type fromBiome(BiomeGenBase bgb) {
        if (bgb != null) {
            if (bgb instanceof LOTRBiomeGenForodwaith) {
                return EntityFox.Type.SNOW;
            }
            else if (bgb instanceof LOTRBiomeGenTundra) {
                int snowFoxChance;
                if (bgb instanceof LOTRBiomeGenTaiga) {

                    //Northern forests (1/6 foxes will be a snow fox)
                    snowFoxChance = mistLotrTweaks$random.nextInt(7);
                }
                else {
                    //Northlands (1 in every 3 foxes will be a snow fox)
                    //Much snowier than northern forests so more snow foxes
                    snowFoxChance = mistLotrTweaks$random.nextInt(4);
                }
                return snowFoxChance > 1 ? EntityFox.Type.RED : EntityFox.Type.SNOW;
            }
            if (bgb instanceof LOTRBiome) return EntityFox.Type.RED;
        }
        return bgb != null && BiomeDictionary.isBiomeOfType(bgb, net.minecraftforge.common.BiomeDictionary.Type.SNOWY) ? EntityFox.Type.SNOW : EntityFox.Type.RED;
    }
}
