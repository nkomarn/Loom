/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.DaylightDetectorBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;

public final class CraftDaylightDetector extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.DaylightDetector, org.bukkit.block.data.AnaloguePowerable {

    public CraftDaylightDetector() {
        super();
    }

    public CraftDaylightDetector(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftDaylightDetector

    private static final BooleanProperty INVERTED = getBoolean(DaylightDetectorBlock.class, "inverted");

    @Override
    public boolean isInverted() {
        return get(INVERTED);
    }

    @Override
    public void setInverted(boolean inverted) {
        set(INVERTED, inverted);
    }

    // org.bukkit.craftbukkit.block.data.CraftAnaloguePowerable

    private static final IntProperty POWER = getInteger(DaylightDetectorBlock.class, "power");

    @Override
    public int getPower() {
        return get(POWER);
    }

    @Override
    public void setPower(int power) {
        set(POWER, power);
    }

    @Override
    public int getMaximumPower() {
        return getMax(POWER);
    }
}
