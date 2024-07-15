package com.github.mistlotrtweaks.config;

import com.github.mistlotrtweaks.MistLotrTweaks;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lotr.common.LOTRDimension;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.feature.LOTRTreeType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MistLotrTweaksBeeHiveConfig {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting()
        .create();

    private static final Type dataType = new TypeToken<Map<String, Map<LOTRTreeType, Map<String, Integer>>>>() {
    }.getType();

    private static Map<String, Map<LOTRTreeType, Map<String, Integer>>> privateData = new HashMap<>();

    /**
     * The upper bound of the random number used for determining if a hive should be spawned.
     * Stored by tree type per biome name.
     */
    public static EnumMap<LOTRTreeType, Map<String, Integer>> data = new EnumMap<>(LOTRTreeType.class);

    public static void loadConfig(File file) {

        if (!file.exists()) {
            // Populate allowed data field
            privateData.put("allowedValues", allowedData());
            // Populate default data field
            privateData.put("data", defaultData());

            try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(file),
                StandardCharsets.UTF_8)) {
                gson.toJson(privateData, dataType, writer);
                writer.flush();
            } catch (IOException ignored) {
                MistLotrTweaks.logger.error("Failed to write beehive config data");
            }
        }

        try (Reader reader = Files.newBufferedReader(
            file.toPath(),
            StandardCharsets.UTF_8)) {
            privateData = gson.fromJson(reader, dataType);
            data = new EnumMap<>(LOTRTreeType.class);
            data.putAll(privateData.get("data"));
        } catch (IOException ignored) {
            MistLotrTweaks.logger.error("Failed to read beehive config data");
        }
    }

    /**
     * Gather all allowed data values so the user knows the options in the config file
     */
    private static EnumMap<LOTRTreeType, Map<String, Integer>> allowedData() {
        EnumMap<LOTRTreeType, Map<String, Integer>> exampleData = new EnumMap<>(LOTRTreeType.class);
        Arrays.stream(LOTRTreeType.values()).forEachOrdered(type -> exampleData.put(type, new HashMap<>()));
        Map<String, Integer> exampleAllowedBiomes = Arrays.stream(LOTRDimension.MIDDLE_EARTH.biomeList).filter(Objects::nonNull).collect(Collectors.toMap(lotrBiome -> lotrBiome.biomeName, biome -> 0));
        exampleData.replace(LOTRTreeType.ACACIA, exampleAllowedBiomes);
        return exampleData;
    }

    /**
     * Default beehive spawn data. Taken from Renewed version 5.5 of the mod and extrapolated for biomes not in renewed
     */
    private static EnumMap<LOTRTreeType, Map<String, Integer>> defaultData() {
        EnumMap<LOTRTreeType, Map<String, Integer>> defaultData = new EnumMap<>(LOTRTreeType.class);

        defaultData.put(LOTRTreeType.OAK, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.gondor.biomeName, 100)
            .put(LOTRBiome.gondorWoodlands.biomeName, 100)
            .put(LOTRBiome.andrast.biomeName, 1000)
            .put(LOTRBiome.anduinVale.biomeName, 100)
            .put(LOTRBiome.celebrant.biomeName, 100)
            .put(LOTRBiome.anduinHills.biomeName, 100)
            .put(LOTRBiome.blackrootVale.biomeName, 100)
            .put(LOTRBiome.breeland.biomeName, 1000)
            .put(LOTRBiome.chetwood.biomeName, 1000)
            .put(LOTRBiome.coldfells.biomeName, 1000)
            .put(LOTRBiome.dale.biomeName, 100)
            .put(LOTRBiome.dorEnErnil.biomeName, 100)
            .put(LOTRBiome.dorEnErnilHills.biomeName, 100)
            .put(LOTRBiome.dorwinion.biomeName, 10)
            .put(LOTRBiome.dorwinionHills.biomeName, 10)
            .put(LOTRBiome.pukel.biomeName, 100)
            .put(LOTRBiome.dunland.biomeName, 100)
            .put(LOTRBiome.enedwaith.biomeName, 1000)
            .put(LOTRBiome.adornland.biomeName, 1000)
            .put(LOTRBiome.eregion.biomeName, 1000)
            .put(LOTRBiome.eriador.biomeName, 1000)
            .put(LOTRBiome.eriadorDowns.biomeName, 1000)
            .put(LOTRBiome.erynVorn.biomeName, 1000)
            .put(LOTRBiome.minhiriath.biomeName, 1000)
            .put(LOTRBiome.fangorn.biomeName, 1000)
            .put(LOTRBiome.ithilien.biomeName, 80)
            .put(LOTRBiome.ithilienHills.biomeName, 80)
            .put(LOTRBiome.lamedon.biomeName, 100)
            .put(LOTRBiome.lamedonHills.biomeName, 100)
            .put(LOTRBiome.lebennin.biomeName, 100)
            .put(LOTRBiome.lindon.biomeName, 1000)
            .put(LOTRBiome.lindonWoodlands.biomeName, 1000)
            .put(LOTRBiome.loneLands.biomeName, 1000)
            .put(LOTRBiome.angle.biomeName, 1000)
            .put(LOTRBiome.loneLandsHills.biomeName, 1000)
            .put(LOTRBiome.lossarnach.biomeName, 10)
            .put(LOTRBiome.imlothMelui.biomeName, 10)
            .put(LOTRBiome.lothlorienEdge.biomeName, 6)
            .put(LOTRBiome.lothlorien.biomeName, 100)
            .put(LOTRBiome.mirkwoodNorth.biomeName, 100)
            .put(LOTRBiome.oldForest.biomeName, 1000)
            .put(LOTRBiome.pelargir.biomeName, 100)
            .put(LOTRBiome.rivendellHills.biomeName, 100)
            .put(LOTRBiome.pinnathGelin.biomeName, 100)
            .put(LOTRBiome.rivendell.biomeName, 100)
            .put(LOTRBiome.wold.biomeName, 100)
            .put(LOTRBiome.rohan.biomeName, 100)
            .put(LOTRBiome.rohanWoodlands.biomeName, 100)
            .put(LOTRBiome.shire.biomeName, 100)
            .put(LOTRBiome.shireMoors.biomeName, 100)
            .put(LOTRBiome.whiteDowns.biomeName, 100)
            .put(LOTRBiome.shireWoodlands.biomeName, 500)
            .put(LOTRBiome.towerHills.biomeName, 1000)
            .put(LOTRBiome.trollshaws.biomeName, 500)
            .put(LOTRBiome.wilderland.biomeName, 1000)
            .put(LOTRBiome.wilderlandNorth.biomeName, 100)
            .put(LOTRBiome.woodlandRealm.biomeName, 500)
            .put(LOTRBiome.woodlandRealmHills.biomeName, 500)
            .put(LOTRBiome.rhun.biomeName, 20)
            .put(LOTRBiome.rhunForest.biomeName, 20)
            .put(LOTRBiome.rhunLand.biomeName, 20)
            .put(LOTRBiome.rhunLandSteppe.biomeName, 20)
            .put(LOTRBiome.rhunRedForest.biomeName, 20)
            .put(LOTRBiome.rhunIsland.biomeName, 20)
            .put(LOTRBiome.rhunIslandForest.biomeName, 20)
            .build()
        );

        defaultData.put(LOTRTreeType.OAK_LARGE, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.gondor.biomeName, 100)
            .put(LOTRBiome.gondorWoodlands.biomeName, 100)
            .put(LOTRBiome.andrast.biomeName, 1000)
            .put(LOTRBiome.anduinVale.biomeName, 100)
            .put(LOTRBiome.celebrant.biomeName, 100)
            .put(LOTRBiome.anduinHills.biomeName, 100)
            .put(LOTRBiome.blackrootVale.biomeName, 100)
            .put(LOTRBiome.breeland.biomeName, 1000)
            .put(LOTRBiome.chetwood.biomeName, 1000)
            .put(LOTRBiome.coldfells.biomeName, 300)
            .put(LOTRBiome.dale.biomeName, 100)
            .put(LOTRBiome.dorEnErnil.biomeName, 100)
            .put(LOTRBiome.dorEnErnilHills.biomeName, 100)
            .put(LOTRBiome.dorwinion.biomeName, 10)
            .put(LOTRBiome.dorwinionHills.biomeName, 10)
            .put(LOTRBiome.pukel.biomeName, 100)
            .put(LOTRBiome.dunland.biomeName, 100)
            .put(LOTRBiome.enedwaith.biomeName, 1000)
            .put(LOTRBiome.adornland.biomeName, 1000)
            .put(LOTRBiome.eregion.biomeName, 1000)
            .put(LOTRBiome.eriador.biomeName, 1000)
            .put(LOTRBiome.eriadorDowns.biomeName, 1000)
            .put(LOTRBiome.erynVorn.biomeName, 1000)
            .put(LOTRBiome.minhiriath.biomeName, 1000)
            .put(LOTRBiome.fangorn.biomeName, 1000)
            .put(LOTRBiome.ithilien.biomeName, 50)
            .put(LOTRBiome.ithilienHills.biomeName, 50)
            .put(LOTRBiome.lamedon.biomeName, 100)
            .put(LOTRBiome.lamedonHills.biomeName, 100)
            .put(LOTRBiome.lebennin.biomeName, 100)
            .put(LOTRBiome.lindon.biomeName, 250)
            .put(LOTRBiome.lindonWoodlands.biomeName, 250)
            .put(LOTRBiome.loneLands.biomeName, 1000)
            .put(LOTRBiome.angle.biomeName, 1000)
            .put(LOTRBiome.loneLandsHills.biomeName, 1000)
            .put(LOTRBiome.lossarnach.biomeName, 10)
            .put(LOTRBiome.imlothMelui.biomeName, 10)
            .put(LOTRBiome.lothlorienEdge.biomeName, 6)
            .put(LOTRBiome.lothlorien.biomeName, 100)
            .put(LOTRBiome.mirkwoodNorth.biomeName, 100)
            .put(LOTRBiome.oldForest.biomeName, 1000)
            .put(LOTRBiome.pelargir.biomeName, 100)
            .put(LOTRBiome.rivendellHills.biomeName, 100)
            .put(LOTRBiome.pinnathGelin.biomeName, 100)
            .put(LOTRBiome.rivendell.biomeName, 100)
            .put(LOTRBiome.wold.biomeName, 100)
            .put(LOTRBiome.rohan.biomeName, 100)
            .put(LOTRBiome.rohanWoodlands.biomeName, 100)
            .put(LOTRBiome.shire.biomeName, 100)
            .put(LOTRBiome.shireMoors.biomeName, 100)
            .put(LOTRBiome.whiteDowns.biomeName, 100)
            .put(LOTRBiome.shireWoodlands.biomeName, 500)
            .put(LOTRBiome.towerHills.biomeName, 100)
            .put(LOTRBiome.trollshaws.biomeName, 1000)
            .put(LOTRBiome.wilderland.biomeName, 1000)
            .put(LOTRBiome.wilderlandNorth.biomeName, 100)
            .put(LOTRBiome.woodlandRealm.biomeName, 1000)
            .put(LOTRBiome.woodlandRealmHills.biomeName, 1000)
            .put(LOTRBiome.rhun.biomeName, 40)
            .put(LOTRBiome.rhunForest.biomeName, 40)
            .put(LOTRBiome.rhunLand.biomeName, 40)
            .put(LOTRBiome.rhunLandSteppe.biomeName, 40)
            .put(LOTRBiome.rhunRedForest.biomeName, 40)
            .put(LOTRBiome.rhunIsland.biomeName, 40)
            .put(LOTRBiome.rhunIslandForest.biomeName, 40)
            .build()
        );

        defaultData.put(LOTRTreeType.OAK_TALL, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.dorEnErnil.biomeName, 100)
            .put(LOTRBiome.dorEnErnilHills.biomeName, 100)
            .put(LOTRBiome.dunland.biomeName, 100)
            .put(LOTRBiome.enedwaith.biomeName, 1000)
            .put(LOTRBiome.adornland.biomeName, 1000)
            .put(LOTRBiome.fangorn.biomeName, 1000)
            .put(LOTRBiome.ithilien.biomeName, 100)
            .put(LOTRBiome.ithilienHills.biomeName, 100)
            .put(LOTRBiome.oldForest.biomeName, 1000)
            .put(LOTRBiome.trollshaws.biomeName, 500)
            .build()
        );

        var applePearRates = new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.andrast.biomeName, 50)
            .put(LOTRBiome.anduinVale.biomeName, 2)
            .put(LOTRBiome.celebrant.biomeName, 2)
            .put(LOTRBiome.anduinHills.biomeName, 2)
            .put(LOTRBiome.blackrootVale.biomeName, 2)
            .put(LOTRBiome.chetwood.biomeName, 2)
            .put(LOTRBiome.breeland.biomeName, 2)
            .put(LOTRBiome.dale.biomeName, 50)
            .put(LOTRBiome.dorEnErnil.biomeName, 50)
            .put(LOTRBiome.dorEnErnilHills.biomeName, 50)
            .put(LOTRBiome.dorwinion.biomeName, 50)
            .put(LOTRBiome.dorwinionHills.biomeName, 50)
            .put(LOTRBiome.pukel.biomeName, 10)
            .put(LOTRBiome.eregion.biomeName, 50)
            .put(LOTRBiome.eriador.biomeName, 20)
            .put(LOTRBiome.eriadorDowns.biomeName, 20)
            .put(LOTRBiome.erynVorn.biomeName, 20)
            .put(LOTRBiome.minhiriath.biomeName, 20)
            .put(LOTRBiome.ithilien.biomeName, 10)
            .put(LOTRBiome.ithilienHills.biomeName, 10)
            .put(LOTRBiome.lamedon.biomeName, 50)
            .put(LOTRBiome.lamedonHills.biomeName, 50)
            .put(LOTRBiome.lebennin.biomeName, 50)
            .put(LOTRBiome.lindon.biomeName, 20)
            .put(LOTRBiome.lindonWoodlands.biomeName, 20)
            .put(LOTRBiome.loneLands.biomeName, 10)
            .put(LOTRBiome.angle.biomeName, 10)
            .put(LOTRBiome.loneLandsHills.biomeName, 10)
            .put(LOTRBiome.lossarnach.biomeName, 10)
            .put(LOTRBiome.imlothMelui.biomeName, 10)
            .put(LOTRBiome.pinnathGelin.biomeName, 50)
            .put(LOTRBiome.rivendell.biomeName, 50)
            .put(LOTRBiome.wold.biomeName, 20)
            .put(LOTRBiome.rohan.biomeName, 20)
            .put(LOTRBiome.rohanWoodlands.biomeName, 20)
            .put(LOTRBiome.shire.biomeName, 50)
            .put(LOTRBiome.shireMoors.biomeName, 50)
            .put(LOTRBiome.whiteDowns.biomeName, 50)
            .put(LOTRBiome.shireWoodlands.biomeName, 50)
            .build();

        defaultData.put(LOTRTreeType.APPLE, applePearRates);
        defaultData.put(LOTRTreeType.PEAR, applePearRates);

        defaultData.put(LOTRTreeType.BIRCH, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.gondor.biomeName, 100)
            .put(LOTRBiome.gondorWoodlands.biomeName, 100)
            .put(LOTRBiome.andrast.biomeName, 500)
            .put(LOTRBiome.anduinVale.biomeName, 500)
            .put(LOTRBiome.celebrant.biomeName, 500)
            .put(LOTRBiome.anduinHills.biomeName, 500)
            .put(LOTRBiome.blackrootVale.biomeName, 100)
            .put(LOTRBiome.chetwood.biomeName, 500)
            .put(LOTRBiome.breeland.biomeName, 500)
            .put(LOTRBiome.dorEnErnil.biomeName, 100)
            .put(LOTRBiome.dorEnErnilHills.biomeName, 100)
            .put(LOTRBiome.dorwinion.biomeName, 100)
            .put(LOTRBiome.dorwinionHills.biomeName, 100)
            .put(LOTRBiome.pukel.biomeName, 100)
            .put(LOTRBiome.eregion.biomeName, 1000)
            .put(LOTRBiome.eriador.biomeName, 1000)
            .put(LOTRBiome.eriadorDowns.biomeName, 1000)
            .put(LOTRBiome.erynVorn.biomeName, 1000)
            .put(LOTRBiome.minhiriath.biomeName, 1000)
            .put(LOTRBiome.fangorn.biomeName, 200)
            .put(LOTRBiome.ithilien.biomeName, 100)
            .put(LOTRBiome.ithilienHills.biomeName, 100)
            .put(LOTRBiome.lamedon.biomeName, 100)
            .put(LOTRBiome.lamedonHills.biomeName, 100)
            .put(LOTRBiome.lebennin.biomeName, 100)
            .put(LOTRBiome.lindon.biomeName, 1000)
            .put(LOTRBiome.lindonWoodlands.biomeName, 1000)
            .put(LOTRBiome.loneLands.biomeName, 100)
            .put(LOTRBiome.angle.biomeName, 100)
            .put(LOTRBiome.loneLandsHills.biomeName, 100)
            .put(LOTRBiome.lossarnach.biomeName, 10)
            .put(LOTRBiome.imlothMelui.biomeName, 10)
            .put(LOTRBiome.pelargir.biomeName, 100)
            .put(LOTRBiome.rivendellHills.biomeName, 100)
            .put(LOTRBiome.pinnathGelin.biomeName, 100)
            .put(LOTRBiome.rivendell.biomeName, 100)
            .put(LOTRBiome.wold.biomeName, 100)
            .put(LOTRBiome.rohan.biomeName, 100)
            .put(LOTRBiome.rohanWoodlands.biomeName, 100)
            .put(LOTRBiome.shire.biomeName, 125)
            .put(LOTRBiome.shireMoors.biomeName, 125)
            .put(LOTRBiome.whiteDowns.biomeName, 125)
            .put(LOTRBiome.shireWoodlands.biomeName, 2500)
            .put(LOTRBiome.towerHills.biomeName, 500)
            .build()
        );

        defaultData.put(LOTRTreeType.BIRCH_LARGE, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.gondor.biomeName, 100)
            .put(LOTRBiome.gondorWoodlands.biomeName, 100)
            .put(LOTRBiome.andrast.biomeName, 200)
            .put(LOTRBiome.anduinVale.biomeName, 500)
            .put(LOTRBiome.anduinHills.biomeName, 500)
            .put(LOTRBiome.blackrootVale.biomeName, 100)
            .put(LOTRBiome.chetwood.biomeName, 200)
            .put(LOTRBiome.breeland.biomeName, 200)
            .put(LOTRBiome.dorEnErnil.biomeName, 100)
            .put(LOTRBiome.dorEnErnilHills.biomeName, 100)
            .put(LOTRBiome.dorwinion.biomeName, 100)
            .put(LOTRBiome.dorwinionHills.biomeName, 100)
            .put(LOTRBiome.pukel.biomeName, 100)
            .put(LOTRBiome.eregion.biomeName, 500)
            .put(LOTRBiome.eriador.biomeName, 100)
            .put(LOTRBiome.eriadorDowns.biomeName, 100)
            .put(LOTRBiome.erynVorn.biomeName, 100)
            .put(LOTRBiome.minhiriath.biomeName, 100)
            .put(LOTRBiome.fangorn.biomeName, 200)
            .put(LOTRBiome.ithilien.biomeName, 100)
            .put(LOTRBiome.ithilienHills.biomeName, 100)
            .put(LOTRBiome.lamedon.biomeName, 100)
            .put(LOTRBiome.lamedonHills.biomeName, 100)
            .put(LOTRBiome.lebennin.biomeName, 100)
            .put(LOTRBiome.lindon.biomeName, 1000)
            .put(LOTRBiome.lindonWoodlands.biomeName, 1000)
            .put(LOTRBiome.loneLands.biomeName, 50)
            .put(LOTRBiome.angle.biomeName, 50)
            .put(LOTRBiome.loneLandsHills.biomeName, 50)
            .put(LOTRBiome.lossarnach.biomeName, 10)
            .put(LOTRBiome.imlothMelui.biomeName, 10)
            .put(LOTRBiome.pelargir.biomeName, 100)
            .put(LOTRBiome.rivendellHills.biomeName, 100)
            .put(LOTRBiome.pinnathGelin.biomeName, 100)
            .put(LOTRBiome.rivendell.biomeName, 100)
            .put(LOTRBiome.wold.biomeName, 100)
            .put(LOTRBiome.rohan.biomeName, 100)
            .put(LOTRBiome.rohanWoodlands.biomeName, 100)
            .put(LOTRBiome.shireWoodlands.biomeName, 100)
            .put(LOTRBiome.towerHills.biomeName, 100)
            .build()
        );

        defaultData.put(LOTRTreeType.BEECH, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.andrast.biomeName, 500)
            .put(LOTRBiome.chetwood.biomeName, 1000)
            .put(LOTRBiome.breeland.biomeName, 1000)
            .put(LOTRBiome.dorwinion.biomeName, 100)
            .put(LOTRBiome.dorwinionHills.biomeName, 100)
            .put(LOTRBiome.pukel.biomeName, 100)
            .put(LOTRBiome.eregion.biomeName, 500)
            .put(LOTRBiome.eriador.biomeName, 200)
            .put(LOTRBiome.eriadorDowns.biomeName, 200)
            .put(LOTRBiome.erynVorn.biomeName, 200)
            .put(LOTRBiome.minhiriath.biomeName, 200)
            .put(LOTRBiome.fangorn.biomeName, 200)
            .put(LOTRBiome.lamedon.biomeName, 100)
            .put(LOTRBiome.lamedonHills.biomeName, 100)
            .put(LOTRBiome.lebennin.biomeName, 100)
            .put(LOTRBiome.lindon.biomeName, 1000)
            .put(LOTRBiome.lindonWoodlands.biomeName, 1000)
            .put(LOTRBiome.loneLands.biomeName, 1000)
            .put(LOTRBiome.angle.biomeName, 1000)
            .put(LOTRBiome.loneLandsHills.biomeName, 1000)
            .put(LOTRBiome.lossarnach.biomeName, 10)
            .put(LOTRBiome.imlothMelui.biomeName, 10)
            .put(LOTRBiome.lothlorienEdge.biomeName, 100)
            .put(LOTRBiome.lothlorien.biomeName, 100)
            .put(LOTRBiome.wold.biomeName, 100)
            .put(LOTRBiome.rohan.biomeName, 100)
            .put(LOTRBiome.rohanWoodlands.biomeName, 100)
            .put(LOTRBiome.trollshaws.biomeName, 1000)
            .put(LOTRBiome.woodlandRealm.biomeName, 500)
            .put(LOTRBiome.woodlandRealmHills.biomeName, 500)
            .build()
        );

        defaultData.put(LOTRTreeType.BEECH_LARGE, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.andrast.biomeName, 200)
            .put(LOTRBiome.chetwood.biomeName, 750)
            .put(LOTRBiome.breeland.biomeName, 750)
            .put(LOTRBiome.dorwinion.biomeName, 100)
            .put(LOTRBiome.dorwinionHills.biomeName, 100)
            .put(LOTRBiome.pukel.biomeName, 100)
            .put(LOTRBiome.eregion.biomeName, 250)
            .put(LOTRBiome.eriador.biomeName, 20)
            .put(LOTRBiome.eriadorDowns.biomeName, 20)
            .put(LOTRBiome.erynVorn.biomeName, 20)
            .put(LOTRBiome.minhiriath.biomeName, 20)
            .put(LOTRBiome.fangorn.biomeName, 200)
            .put(LOTRBiome.lamedon.biomeName, 100)
            .put(LOTRBiome.lamedonHills.biomeName, 100)
            .put(LOTRBiome.lebennin.biomeName, 100)
            .put(LOTRBiome.lindon.biomeName, 250)
            .put(LOTRBiome.lindonWoodlands.biomeName, 250)
            .put(LOTRBiome.loneLands.biomeName, 500)
            .put(LOTRBiome.angle.biomeName, 500)
            .put(LOTRBiome.loneLandsHills.biomeName, 500)
            .put(LOTRBiome.lossarnach.biomeName, 10)
            .put(LOTRBiome.imlothMelui.biomeName, 10)
            .put(LOTRBiome.lothlorienEdge.biomeName, 100)
            .put(LOTRBiome.lothlorien.biomeName, 100)
            .put(LOTRBiome.wold.biomeName, 100)
            .put(LOTRBiome.rohan.biomeName, 100)
            .put(LOTRBiome.rohanWoodlands.biomeName, 100)
            .put(LOTRBiome.trollshaws.biomeName, 1000)
            .put(LOTRBiome.woodlandRealm.biomeName, 1000)
            .put(LOTRBiome.woodlandRealmHills.biomeName, 1000)
            .build()
        );

        defaultData.put(LOTRTreeType.MAPLE, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.chetwood.biomeName, 1000)
            .put(LOTRBiome.breeland.biomeName, 1000)
            .put(LOTRBiome.coldfells.biomeName, 500)
            .put(LOTRBiome.dale.biomeName, 100)
            .put(LOTRBiome.eriador.biomeName, 50)
            .put(LOTRBiome.eriadorDowns.biomeName, 50)
            .put(LOTRBiome.erynVorn.biomeName, 50)
            .put(LOTRBiome.minhiriath.biomeName, 50)
            .put(LOTRBiome.lebennin.biomeName, 100)
            .put(LOTRBiome.loneLands.biomeName, 50)
            .put(LOTRBiome.angle.biomeName, 50)
            .put(LOTRBiome.loneLandsHills.biomeName, 50)
            .put(LOTRBiome.lossarnach.biomeName, 10)
            .put(LOTRBiome.imlothMelui.biomeName, 10)
            .put(LOTRBiome.trollshaws.biomeName, 50)
            .put(LOTRBiome.rhun.biomeName, 20)
            .put(LOTRBiome.rhunForest.biomeName, 20)
            .put(LOTRBiome.rhunLand.biomeName, 20)
            .put(LOTRBiome.rhunLandSteppe.biomeName, 20)
            .put(LOTRBiome.rhunRedForest.biomeName, 20)
            .put(LOTRBiome.rhunIsland.biomeName, 20)
            .put(LOTRBiome.rhunIslandForest.biomeName, 20)
            .build()
        );

        defaultData.put(LOTRTreeType.MAPLE_LARGE, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.chetwood.biomeName, 500)
            .put(LOTRBiome.breeland.biomeName, 500)
            .put(LOTRBiome.coldfells.biomeName, 50)
            .put(LOTRBiome.dale.biomeName, 100)
            .put(LOTRBiome.eriador.biomeName, 5)
            .put(LOTRBiome.eriadorDowns.biomeName, 5)
            .put(LOTRBiome.erynVorn.biomeName, 5)
            .put(LOTRBiome.minhiriath.biomeName, 5)
            .put(LOTRBiome.loneLands.biomeName, 5)
            .put(LOTRBiome.angle.biomeName, 5)
            .put(LOTRBiome.loneLandsHills.biomeName, 5)
            .put(LOTRBiome.lossarnach.biomeName, 10)
            .put(LOTRBiome.trollshaws.biomeName, 20)
            .put(LOTRBiome.rhun.biomeName, 20)
            .put(LOTRBiome.rhunForest.biomeName, 20)
            .put(LOTRBiome.rhunLand.biomeName, 20)
            .put(LOTRBiome.rhunLandSteppe.biomeName, 20)
            .put(LOTRBiome.rhunRedForest.biomeName, 20)
            .put(LOTRBiome.rhunIsland.biomeName, 20)
            .put(LOTRBiome.rhunIslandForest.biomeName, 20)
            .build()
        );

        var hollyRates = new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.eregion.biomeName, 1000)
            .build();

        defaultData.put(LOTRTreeType.HOLLY, hollyRates);
        defaultData.put(LOTRTreeType.HOLLY_LARGE, hollyRates);

        defaultData.put(LOTRTreeType.OAK_DESERT, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.harondor.biomeName, 1000)
            .put(LOTRBiome.nearHaradFertile.biomeName, 100)
            .put(LOTRBiome.nearHaradFertileForest.biomeName, 100)
            .put(LOTRBiome.gulfHarad.biomeName, 100)
            .put(LOTRBiome.gulfHaradForest.biomeName, 100)
            .put(LOTRBiome.umbar.biomeName, 1000)
            .put(LOTRBiome.umbarForest.biomeName, 1000)
            .put(LOTRBiome.umbarHills.biomeName, 1000)
            .build()
        );

        defaultData.put(LOTRTreeType.CHERRY, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.shire.biomeName, 20)
            .put(LOTRBiome.shireMoors.biomeName, 20)
            .put(LOTRBiome.whiteDowns.biomeName, 20)
            .put(LOTRBiome.shireWoodlands.biomeName, 20)
            .build()
        );

        defaultData.put(LOTRTreeType.MALLORN, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.lothlorien.biomeName, 100)
            .put(LOTRBiome.lothlorienEdge.biomeName, 100)
            .build()
        );

        defaultData.put(LOTRTreeType.GREEN_OAK, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.mirkwoodNorth.biomeName, 100)
            .put(LOTRBiome.woodlandRealm.biomeName, 1000)
            .put(LOTRBiome.woodlandRealmHills.biomeName, 1000)
            .build()
        );

        defaultData.put(LOTRTreeType.RED_OAK, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.woodlandRealm.biomeName, 80)
            .put(LOTRBiome.woodlandRealmHills.biomeName, 80)
            .build()
        );

        defaultData.put(LOTRTreeType.LEBETHRON, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.ithilien.biomeName, 100)
            .put(LOTRBiome.ithilienHills.biomeName, 100)
            .build()
        );

        defaultData.put(LOTRTreeType.LEBETHRON_LARGE, new ImmutableMap.Builder<String, Integer>()
            .put(LOTRBiome.ithilien.biomeName, 100)
            .put(LOTRBiome.ithilienHills.biomeName, 100)
            .build()
        );

        return defaultData;
    }
}
