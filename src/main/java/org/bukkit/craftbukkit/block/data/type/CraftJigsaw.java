package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.EnumProperty;
import org.bukkit.block.data.type.Jigsaw;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public abstract class CraftJigsaw extends CraftBlockData implements Jigsaw {

    private static final EnumProperty<?> ORIENTATION = getEnum("orientation");

    @Override
    public Orientation getOrientation() {
        return get(ORIENTATION, Orientation.class);
    }

    @Override
    public void setOrientation(Orientation orientation) {
        set(ORIENTATION, orientation);
    }
}
