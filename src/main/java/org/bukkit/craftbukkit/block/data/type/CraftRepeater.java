package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public abstract class CraftRepeater extends CraftBlockData implements Repeater {

    private static final IntProperty DELAY = getInteger("delay");
    private static final BooleanProperty LOCKED = getBoolean("locked");

    @Override
    public int getDelay() {
        return get(DELAY);
    }

    @Override
    public void setDelay(int delay) {
        set(DELAY, delay);
    }

    @Override
    public int getMinimumDelay() {
        return getMin(DELAY);
    }

    @Override
    public int getMaximumDelay() {
        return getMax(DELAY);
    }

    @Override
    public boolean isLocked() {
        return get(LOCKED);
    }

    @Override
    public void setLocked(boolean locked) {
        set(LOCKED, locked);
    }
}
