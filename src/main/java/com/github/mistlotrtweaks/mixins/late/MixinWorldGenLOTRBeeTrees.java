package com.github.mistlotrtweaks.mixins.late;

import com.github.mistlotrtweaks.compat.EFRCompat;
import com.github.mistlotrtweaks.util.LOTRTreeTypeObtainer;
import ganymedes01.etfuturum.core.handlers.WorldEventHandler;
import lotr.common.world.feature.LOTRWorldGenBigTrees;
import lotr.common.world.feature.LOTRWorldGenHolly;
import lotr.common.world.feature.LOTRWorldGenMirkOak;
import lotr.common.world.feature.LOTRWorldGenSimpleTrees;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

/**
 * Adds beehive placement logic to the types of trees that get bee hives in the 1.16.5 version of the lotr mod
 */
@Mixin(value = {LOTRWorldGenSimpleTrees.class, LOTRWorldGenBigTrees.class, LOTRWorldGenMirkOak.class, LOTRWorldGenHolly.class}, remap = false)
public abstract class MixinWorldGenLOTRBeeTrees implements LOTRTreeTypeObtainer {

    @Inject(method = "generate(Lnet/minecraft/world/World;Ljava/util/Random;III)Z", at = @At("TAIL"))
    public void generateLOTRHive(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (EFRCompat.hiveChancePerBiomeAndTreeType(world, x, z, random, getTreeType())) {
            WorldEventHandler.tryPlaceBeeNest(world, x, y, z, random, 3);
        }
    }
}
