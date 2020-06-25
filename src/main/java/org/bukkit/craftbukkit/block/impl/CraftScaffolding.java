/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;

public final class CraftScaffolding extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Scaffolding, org.bukkit.block.data.Waterlogged {

    public CraftScaffolding() {
        super();
    }

    public CraftScaffolding(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftScaffolding

    private static final BooleanProperty BOTTOM = getBoolean(ScaffoldingBlock.class, "bottom");
    private static final IntProperty DISTANCE = getInteger(ScaffoldingBlock.class, "distance");

    @Override
    public boolean isBottom() {
        return get(BOTTOM);
    }

    @Override
    public void setBottom(boolean bottom) {
        set(BOTTOM, bottom);
    }

    @Override
    public int getDistance() {
        return get(DISTANCE);
    }

    @Override
    public void setDistance(int distance) {
        set(DISTANCE, distance);
    }

    @Override
    public int getMaximumDistance() {
        return getMax(DISTANCE);
    }

    // org.bukkit.craftbukkit.block.data.CraftWaterlogged

    private static final BooleanProperty WATERLOGGED = getBoolean(ScaffoldingBlock.class, "waterlogged");

    @Override
    public boolean isWaterlogged() {
        return get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(boolean waterlogged) {
        set(WATERLOGGED, waterlogged);
    }
}
