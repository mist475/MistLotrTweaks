package com.github.mistlotrtweaks.mixins.late;

import com.github.mistlotrtweaks.config.MistLotrTweaksConfig;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeDecorator;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenMinable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LOTRBiome.class, remap = false)
public abstract class MixinLOTRBiome {
    @Shadow(remap = false)
    public LOTRBiomeDecorator decorator;

    @Inject(method = "<init>(IZ)V", at = @At(value = "TAIL", remap = false), remap = false)
    public void addRedstoneOreToRedMountains(int i, boolean major, CallbackInfo ci) {
        this.decorator.addOre(new WorldGenMinable(Blocks.redstone_ore, MistLotrTweaksConfig.redstoneVeinSize), MistLotrTweaksConfig.redstoneRarity, MistLotrTweaksConfig.redstoneMinYLevel, MistLotrTweaksConfig.redstoneMaxYLevel);
    }
}
