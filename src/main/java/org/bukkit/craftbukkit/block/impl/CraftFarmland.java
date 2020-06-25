/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.state.property.IntProperty;

public final class CraftFarmland extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Farmland {

    public CraftFarmland() {
        super();
    }

    public CraftFarmland(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftFarmland

    private static final IntProperty MOISTURE = getInteger(FarmlandBlock.class, "moisture");

    @Override
    public int getMoisture() {
        return get(MOISTURE);
    }

    @Override
    public void setMoisture(int moisture) {
        set(MOISTURE, moisture);
    }

    @Override
    public int getMaximumMoisture() {
        return getMax(MOISTURE);
    }
}
