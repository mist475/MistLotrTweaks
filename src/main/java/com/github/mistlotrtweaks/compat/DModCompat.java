package com.github.mistlotrtweaks.compat;

import com.github.mistlotrtweaks.config.MistLotrTweaksConfig;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.entity.EnumCreatureType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.stream.Stream;

public class DModCompat {

    /**
     * Values taken from renewed
     */
    public static void addDModFoxes() {
        if (Loader.isModLoaded("dmod") && MistLotrTweaksConfig.addDModFoxesToME) {
            Stream.of(
                Pair.of(LOTRBiome.chetwood, 2),
                Pair.of(LOTRBiome.pukel, 1),
                Pair.of(LOTRBiome.dunland, 2),
                Pair.of(LOTRBiome.eregion, 1),
                Pair.of(LOTRBiome.erynVorn, 1),
                Pair.of(LOTRBiome.ithilien, 1),
                Pair.of(LOTRBiome.mirkwoodNorth, 1),
                Pair.of(LOTRBiome.tundra, 2),
                Pair.of(LOTRBiome.shireWoodlands, 2),
                Pair.of(LOTRBiome.trollshaws, 1)
            ).forEach(biome -> EntityRegistry.addSpawn(
                "dmod.fox",
                MistLotrTweaksConfig.baseFoxSpawnChance * biome.getRight(),
                MistLotrTweaksConfig.minimumFoxSpawnGroup,
                MistLotrTweaksConfig.maximumFoxSpawnGroup,
                EnumCreatureType.creature,
                biome.getLeft()
            ));
        }
    }
}
