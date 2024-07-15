package com.github.mistlotrtweaks.mixins.early;

import com.github.mistlotrtweaks.util.LOTRTreeTypeObtainer;
import lotr.common.world.feature.LOTRTreeType;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * Adds a LOTRTreeType field to all abstract tree classes and adds getters/setters for these. Used for retrieving the tree type when determining beehive spawn chance
 */
@Mixin(WorldGenAbstractTree.class)
public abstract class MixinWorldGenAbstractTree implements LOTRTreeTypeObtainer {
    @Unique
    private LOTRTreeType mistLotrTweaks$treeType;

    protected MixinWorldGenAbstractTree(LOTRTreeType treeType) {
        this.mistLotrTweaks$treeType = treeType;
    }

    public void setTreeType(LOTRTreeType type) {
        mistLotrTweaks$treeType = type;
    }
    public LOTRTreeType getTreeType() {
        return mistLotrTweaks$treeType;
    }
}
