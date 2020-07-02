package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.BooleanProperty;
import org.bukkit.block.data.type.PistonHead;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public abstract class CraftPistonHead extends CraftBlockData implements PistonHead {

    private static final BooleanProperty SHORT = getBoolean("short");

    @Override
    public boolean isShort() {
        return get(SHORT);
    }

    @Override
    public void setShort(boolean _short) {
        set(SHORT, _short);
    }
}
