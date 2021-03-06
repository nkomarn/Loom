/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public final class CraftFurnace extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Furnace, org.bukkit.block.data.Directional, org.bukkit.block.data.Lightable {

    public CraftFurnace() {
        super();
    }

    public CraftFurnace(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.CraftDirectional

    private static final EnumProperty<?> FACING = getEnum(FurnaceBlock.class, "facing");

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

    // org.bukkit.craftbukkit.block.data.CraftLightable

    private static final BooleanProperty LIT = getBoolean(FurnaceBlock.class, "lit");

    @Override
    public boolean isLit() {
        return get(LIT);
    }

    @Override
    public void setLit(boolean lit) {
        set(LIT, lit);
    }
}
