/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public final class CraftDispenser extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Dispenser, org.bukkit.block.data.Directional {

    public CraftDispenser() {
        super();
    }

    public CraftDispenser(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftDispenser

    private static final BooleanProperty TRIGGERED = getBoolean(DispenserBlock.class, "triggered");

    @Override
    public boolean isTriggered() {
        return get(TRIGGERED);
    }

    @Override
    public void setTriggered(boolean triggered) {
        set(TRIGGERED, triggered);
    }

    // org.bukkit.craftbukkit.block.data.CraftDirectional

    private static final EnumProperty<?> FACING = getEnum(DispenserBlock.class, "facing");

    @Override
    public org.bukkit.block.BlockFace getFacing() {
        return get(FACING, org.bukkit.block.BlockFace.class);
    }

    @Override
    public void setFacing(org.bukkit.block.BlockFace facing) {
        set(FACING, facing);
    }

    @Override
    public java.util.Set<org.bukkit.block.BlockFace> getFaces() {
        return getValues(FACING, org.bukkit.block.BlockFace.class);
    }
}
