/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public final class CraftBeehive extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Beehive, org.bukkit.block.data.Directional {

    public CraftBeehive() {
        super();
    }

    public CraftBeehive(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftBeehive

    private static final IntProperty HONEY_LEVEL = getInteger(BeehiveBlock.class, "honey_level");

    @Override
    public int getHoneyLevel() {
        return get(HONEY_LEVEL);
    }

    @Override
    public void setHoneyLevel(int honeyLevel) {
        set(HONEY_LEVEL, honeyLevel);
    }

    @Override
    public int getMaximumHoneyLevel() {
        return getMax(HONEY_LEVEL);
    }

    // org.bukkit.craftbukkit.block.data.CraftDirectional

    private static final EnumProperty<?> FACING = getEnum(BeehiveBlock.class, "facing");

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
