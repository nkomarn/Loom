/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;

public final class CraftStepAbstract extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Slab, org.bukkit.block.data.Waterlogged {

    public CraftStepAbstract() {
        super();
    }

    public CraftStepAbstract(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftSlab

    private static final EnumProperty<?> TYPE = getEnum(SlabBlock.class, "type");

    @Override
    public Type getType() {
        return get(TYPE, Type.class);
    }

    @Override
    public void setType(Type type) {
        set(TYPE, type);
    }

    // org.bukkit.craftbukkit.block.data.CraftWaterlogged

    private static final BooleanProperty WATERLOGGED = getBoolean(SlabBlock.class, "waterlogged");

    @Override
    public boolean isWaterlogged() {
        return get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(boolean waterlogged) {
        set(WATERLOGGED, waterlogged);
    }
}
