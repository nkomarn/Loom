package org.bukkit.craftbukkit.block.data;

import net.minecraft.state.property.IntProperty;
import org.bukkit.block.data.AnaloguePowerable;

public abstract class CraftAnaloguePowerable extends CraftBlockData implements AnaloguePowerable {

    private static final IntProperty POWER = getInteger("power");

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
