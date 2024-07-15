package com.github.mistlotrtweaks.mixins.late;

import com.github.mistlotrtweaks.util.LOTRTreeTypeObtainer;
import lotr.common.world.feature.LOTRTreeType;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

/**
 * Set the LOTRTreeType for any lotr tree, so it can be retrieved when determining bee if a bee hive spawn should be attempted
 */
@Mixin(value = LOTRTreeType.class, remap = false)
public class MixinLOTRTreeType {

    @Inject(method = "create(ZLjava/util/Random;)Lnet/minecraft/world/gen/feature/WorldGenAbstractTree;", at = @At("TAIL"), remap = false)
    public void generateExperiment(boolean flag, Random rand, CallbackInfoReturnable<WorldGenAbstractTree> cir) {
        ((LOTRTreeTypeObtainer) cir.getReturnValue()).setTreeType((LOTRTreeType) (Object) this);
    }
}
