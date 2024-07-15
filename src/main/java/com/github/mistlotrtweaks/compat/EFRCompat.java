package com.github.mistlotrtweaks.compat;

import com.github.mistlotrtweaks.MistLotrTweaks;
import com.github.mistlotrtweaks.config.MistLotrTweaksBeeHiveConfig;
import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.api.BeePlantRegistry;
import ganymedes01.etfuturum.configuration.configs.ConfigBlocksItems;
import ganymedes01.etfuturum.configuration.configs.ConfigEntities;
import lotr.common.LOTRMod;
import lotr.common.recipe.LOTRBrewingRecipes;
import lotr.common.world.feature.LOTRTreeType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class EFRCompat {


    /**
     * Calculate if a bee hive spawn should be attempted
     */
    public static boolean hiveChancePerBiomeAndTreeType(World world, int x, int z, Random random, LOTRTreeType type) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        var data = MistLotrTweaksBeeHiveConfig.data;

        if (data.containsKey(type)) {
            var weightMap = data.get(type);
            if (weightMap.containsKey(biome.biomeName)) {
                return random.nextInt(weightMap.get(biome.biomeName)) == 0;
            }
        }
        return false;
    }

    public static void EFRCompatPostInit() {
        if (ConfigBlocksItems.enableHoney) {
            registerRecipes();
        }
        if (ConfigEntities.enableBees) {
            registerLOTRFlowers();
        }
    }

    /**
     * Registers the lotr flowers and crops that are missed by the automatic efr check
     */
    public static void registerLOTRFlowers() {
        BeePlantRegistry.addCrop(LOTRMod.berryBush);
        BeePlantRegistry.addCrop(LOTRMod.cornStalk);
        BeePlantRegistry.addCrop(LOTRMod.grapevineRed);
        BeePlantRegistry.addCrop(LOTRMod.grapevineWhite);

        BeePlantRegistry.addFlower(LOTRMod.doubleFlower, 0);
        BeePlantRegistry.addFlower(LOTRMod.doubleFlower, 1);
        BeePlantRegistry.addFlower(LOTRMod.doubleFlower, 2);
        BeePlantRegistry.addFlower(LOTRMod.doubleFlower, 3);

        BeePlantRegistry.addFlower(LOTRMod.haradFlower, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.morgulFlower, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.pipeweedPlant, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.rhunFlower, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.athelas, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.simbelmyne, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.shireHeather, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.elanor, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.niphredil, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.bluebell, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.dwarfHerb, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.marigold, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.lavender, OreDictionary.WILDCARD_VALUE);
        BeePlantRegistry.addFlower(LOTRMod.fangornPlant, OreDictionary.WILDCARD_VALUE);
    }

    @SuppressWarnings("unchecked")
    public static void registerRecipes() {

        // Remove the mead brewing recipe and add a new recipe using honey bottles
        try {
            Field lotrBrewingRecipes = LOTRBrewingRecipes.class.getDeclaredField("recipes");
            lotrBrewingRecipes.setAccessible(true);
            ArrayList<ShapelessOreRecipe> recipes = (ArrayList<ShapelessOreRecipe>) lotrBrewingRecipes.get(null);

            // remove already present mead recipe
            var adjusted = recipes
                .stream()
                .filter(recipe -> !LOTRMod.mugMead.equals(recipe.getRecipeOutput().getItem()))
                .collect(Collectors.toCollection(ArrayList::new));

            var honeyBottle = GameRegistry.findItem("etfuturum", "honey_bottle");

            adjusted.add(new ShapelessOreRecipe(
                new ItemStack(LOTRMod.mugMead, LOTRBrewingRecipes.BARREL_CAPACITY),
                honeyBottle,
                honeyBottle,
                honeyBottle,
                honeyBottle,
                honeyBottle,
                honeyBottle)
            );

            lotrBrewingRecipes.set(null, adjusted);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            MistLotrTweaks.logger.warn("Failed to adjust recipes");
        }
    }
}
