/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public final class CraftPoweredRail extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.RedstoneRail, org.bukkit.block.data.Powerable, org.bukkit.block.data.Rail {

    public CraftPoweredRail() {
        super();
    }

    public CraftPoweredRail(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.CraftPowerable

    private static final BooleanProperty POWERED = getBoolean(PoweredRailBlock.class, "powered");

    @Override
    public boolean isPowered() {
        return get(POWERED);
    }

    @Override
    public void setPowered(boolean powered) {
        set(POWERED, powered);
    }

    // org.bukkit.craftbukkit.block.data.CraftRail

    private static final EnumProperty<?> SHAPE = getEnum(PoweredRailBlock.class, "shape");

    @Override
    public Shape getShape() {
        return get(SHAPE, Shape.class);
    }

    @Override
    public void setShape(Shape shape) {
        set(SHAPE, shape);
    }

    @Override
    public java.util.Set<Shape> getShapes() {
        return getValues(SHAPE, Shape.class);
    }
}
