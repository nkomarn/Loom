package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.BooleanProperty;
import org.bukkit.block.data.type.Lantern;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public abstract class CraftLantern extends CraftBlockData implements Lantern {

    private static final BooleanProperty HANGING = getBoolean("hanging");

    @Override
    public boolean isHanging() {
        return get(HANGING);
    }

    @Override
    public void setHanging(boolean hanging) {
        set(HANGING, hanging);
    }
}
