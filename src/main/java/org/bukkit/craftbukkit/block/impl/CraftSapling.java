/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.state.property.IntProperty;

public final class CraftSapling extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Sapling {

    public CraftSapling() {
        super();
    }

    public CraftSapling(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftSapling

    private static final IntProperty STAGE = getInteger(SaplingBlock.class, "stage");

    @Override
    public int getStage() {
        return get(STAGE);
    }

    @Override
    public void setStage(int stage) {
        set(STAGE, stage);
    }

    @Override
    public int getMaximumStage() {
        return getMax(STAGE);
    }
}
