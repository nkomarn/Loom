/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public final class CraftBamboo extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Bamboo, org.bukkit.block.data.Ageable, org.bukkit.block.data.type.Sapling {

    public CraftBamboo() {
        super();
    }

    public CraftBamboo(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftBamboo

    private static final EnumProperty<?> LEAVES = getEnum(BambooBlock.class, "leaves");

    @Override
    public Leaves getLeaves() {
        return get(LEAVES, Leaves.class);
    }

    @Override
    public void setLeaves(Leaves leaves) {
        set(LEAVES, leaves);
    }

    // org.bukkit.craftbukkit.block.data.CraftAgeable

    private static final IntProperty AGE = getInteger(BambooBlock.class, "age");

    @Override
    public int getAge() {
        return get(AGE);
    }

    @Override
    public void setAge(int age) {
        set(AGE, age);
    }

    @Override
    public int getMaximumAge() {
        return getMax(AGE);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftSapling

    private static final IntProperty STAGE = getInteger(BambooBlock.class, "stage");

    @Override
    public int getStage() {
        return get(STAGE);
    }

    @Override
    public void setStage(int stage) {
        set(STAGE, stage);
    }

    @Override
    public int getMaximumStage() {
        return getMax(STAGE);
    }
}
