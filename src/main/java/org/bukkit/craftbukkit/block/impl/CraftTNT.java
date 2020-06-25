/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.TntBlock;
import net.minecraft.state.property.BooleanProperty;

public final class CraftTNT extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.TNT {

    public CraftTNT() {
        super();
    }

    public CraftTNT(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftTNT

    private static final BooleanProperty UNSTABLE = getBoolean(TntBlock.class, "unstable");

    @Override
    public boolean isUnstable() {
        return get(UNSTABLE);
    }

    @Override
    public void setUnstable(boolean unstable) {
        set(UNSTABLE, unstable);
    }
}
