/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.HayBlock;
import net.minecraft.state.property.EnumProperty;

public final class CraftHay extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.Orientable {

    public CraftHay() {
        super();
    }

    public CraftHay(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.CraftOrientable

    private static final EnumProperty<?> AXIS = getEnum(HayBlock.class, "axis");

    @Override
    public org.bukkit.Axis getAxis() {
        return get(AXIS, org.bukkit.Axis.class);
    }

    @Override
    public void setAxis(org.bukkit.Axis axis) {
        set(AXIS, axis);
    }

    @Override
    public java.util.Set<org.bukkit.Axis> getAxes() {
        return getValues(AXIS, org.bukkit.Axis.class);
    }
}
