/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.state.property.IntProperty;

public final class CraftTurtleEgg extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.TurtleEgg {

    public CraftTurtleEgg() {
        super();
    }

    public CraftTurtleEgg(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftTurtleEgg

    private static final IntProperty EGGS = getInteger(TurtleEggBlock.class, "eggs");
    private static final IntProperty HATCH = getInteger(TurtleEggBlock.class, "hatch");

    @Override
    public int getEggs() {
        return get(EGGS);
    }

    @Override
    public void setEggs(int eggs) {
        set(EGGS, eggs);
    }

    @Override
    public int getMinimumEggs() {
        return getMin(EGGS);
    }

    @Override
    public int getMaximumEggs() {
        return getMax(EGGS);
    }

    @Override
    public int getHatch() {
        return get(HATCH);
    }

    @Override
    public void setHatch(int hatch) {
        set(HATCH, hatch);
    }

    @Override
    public int getMaximumHatch() {
        return getMax(HATCH);
    }
}
