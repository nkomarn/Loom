/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BellBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.EnumProperty;

public final class CraftBell extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Bell, org.bukkit.block.data.Directional {

    public CraftBell() {
        super();
    }

    public CraftBell(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftBell

    private static final EnumProperty<?> ATTACHMENT = getEnum(BellBlock.class, "attachment");

    @Override
    public Attachment getAttachment() {
        return get(ATTACHMENT, Attachment.class);
    }

    @Override
    public void setAttachment(Attachment leaves) {
        set(ATTACHMENT, leaves);
    }

    // org.bukkit.craftbukkit.block.data.CraftDirectional

    private static final EnumProperty<?> FACING = getEnum(BellBlock.class, "facing");

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
