/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.StructureBlock;
import net.minecraft.state.property.EnumProperty;

public final class CraftStructure extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.StructureBlock {

    public CraftStructure() {
        super();
    }

    public CraftStructure(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftStructureBlock

    private static final EnumProperty<?> MODE = getEnum(StructureBlock.class, "mode");

    @Override
    public Mode getMode() {
        return get(MODE, Mode.class);
    }

    @Override
    public void setMode(Mode mode) {
        set(MODE, mode);
    }
}
