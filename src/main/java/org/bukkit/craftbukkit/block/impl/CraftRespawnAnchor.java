/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.state.property.IntProperty;

public final class CraftRespawnAnchor extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.RespawnAnchor {

    public CraftRespawnAnchor() {
        super();
    }

    public CraftRespawnAnchor(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftRespawnAnchor

    private static final IntProperty CHARGES = getInteger(RespawnAnchorBlock.class, "charges");

    @Override
    public int getCharges() {
        return get(CHARGES);
    }

    @Override
    public void setCharges(int charges) {
        set(CHARGES, charges);
    }

    @Override
    public int getMaximumCharges() {
        return getMax(CHARGES);
    }
}
