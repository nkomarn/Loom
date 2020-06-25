/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public final class CraftBed extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Bed, org.bukkit.block.data.Directional {

    public CraftBed() {
        super();
    }

    public CraftBed(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftBed

    private static final EnumProperty<?> PART = getEnum(BedBlock.class, "part");
    private static final BooleanProperty OCCUPIED = getBoolean(BedBlock.class, "occupied");

    @Override
    public Part getPart() {
        return get(PART, Part.class);
    }

    @Override
    public void setPart(Part part) {
        set(PART, part);
    }

    @Override
    public boolean isOccupied() {
        return get(OCCUPIED);
    }

    // org.bukkit.craftbukkit.block.data.CraftDirectional

    private static final EnumProperty<?> FACING = getEnum(BedBlock.class, "facing");

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
