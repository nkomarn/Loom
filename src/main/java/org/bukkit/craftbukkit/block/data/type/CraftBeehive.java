package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.IntProperty;
import org.bukkit.block.data.type.Beehive;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public abstract class CraftBeehive extends CraftBlockData implements Beehive {

    private static final IntProperty HONEY_LEVEL = getInteger("honey_level");

    @Override
    public int getHoneyLevel() {
        return get(HONEY_LEVEL);
    }

    @Override
    public void setHoneyLevel(int honeyLevel) {
        set(HONEY_LEVEL, honeyLevel);
    }

    @Override
    public int getMaximumHoneyLevel() {
        return getMax(HONEY_LEVEL);
    }
}
