/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.ComparatorBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public final class CraftRedstoneComparator extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Comparator, org.bukkit.block.data.Directional, org.bukkit.block.data.Powerable {

    public CraftRedstoneComparator() {
        super();
    }

    public CraftRedstoneComparator(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftComparator

    private static final EnumProperty<?> MODE = getEnum(ComparatorBlock.class, "mode");

    @Override
    public Mode getMode() {
        return get(MODE, Mode.class);
    }

    @Override
    public void setMode(Mode mode) {
        set(MODE, mode);
    }

    // org.bukkit.craftbukkit.block.data.CraftDirectional

    private static final EnumProperty<?> FACING = getEnum(ComparatorBlock.class, "facing");

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

    // org.bukkit.craftbukkit.block.data.CraftPowerable

    private static final BooleanProperty POWERED = getBoolean(ComparatorBlock.class, "powered");

    @Override
    public boolean isPowered() {
        return get(POWERED);
    }

    @Override
    public void setPowered(boolean powered) {
        set(POWERED, powered);
    }
}
