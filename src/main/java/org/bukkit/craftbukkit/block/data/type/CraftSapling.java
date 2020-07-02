package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.IntProperty;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public abstract class CraftSapling extends CraftBlockData implements Sapling {

    private static final IntProperty STAGE = getInteger("stage");

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
