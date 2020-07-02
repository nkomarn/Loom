package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.IntProperty;
import org.bukkit.block.data.type.Cake;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public abstract class CraftCake extends CraftBlockData implements Cake {

    private static final IntProperty BITES = getInteger("bites");

    @Override
    public int getBites() {
        return get(BITES);
    }

    @Override
    public void setBites(int bites) {
        set(BITES, bites);
    }

    @Override
    public int getMaximumBites() {
        return getMax(BITES);
    }
}
