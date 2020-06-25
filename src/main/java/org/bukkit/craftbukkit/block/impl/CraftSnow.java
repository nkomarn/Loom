/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.state.property.IntProperty;

public final class CraftSnow extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Snow {

    public CraftSnow() {
        super();
    }

    public CraftSnow(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftSnow

    private static final IntProperty LAYERS = getInteger(SnowBlock.class, "layers");

    @Override
    public int getLayers() {
        return get(LAYERS);
    }

    @Override
    public void setLayers(int layers) {
        set(LAYERS, layers);
    }

    @Override
    public int getMinimumLayers() {
        return getMin(LAYERS);
    }

    @Override
    public int getMaximumLayers() {
        return getMax(LAYERS);
    }
}
