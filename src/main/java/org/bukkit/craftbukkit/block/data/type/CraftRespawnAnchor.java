package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.IntProperty;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public abstract class CraftRespawnAnchor extends CraftBlockData implements RespawnAnchor {

    private static final IntProperty CHARGES = getInteger("charges");

    @Override
    public int getCharges() {
        return get(CHARGES);
    }

    @Override
    public void setCharges(int charges) {
        set(CHARGES, charges);
    }

    @Override
    public int getMaximumCharges() {
        return getMax(CHARGES);
    }
}
