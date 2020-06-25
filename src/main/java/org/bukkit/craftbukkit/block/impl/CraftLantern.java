/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.state.property.BooleanProperty;

public final class CraftLantern extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Lantern {

    public CraftLantern() {
        super();
    }

    public CraftLantern(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftLantern

    private static final BooleanProperty HANGING = getBoolean(LanternBlock.class, "hanging");

    @Override
    public boolean isHanging() {
        return get(HANGING);
    }

    @Override
    public void setHanging(boolean hanging) {
        set(HANGING, hanging);
    }
}
