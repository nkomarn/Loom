package org.bukkit.craftbukkit.block.data;

import net.minecraft.state.property.BooleanProperty;
import org.bukkit.block.data.Attachable;

public abstract class CraftAttachable extends CraftBlockData implements Attachable {

    private static final BooleanProperty ATTACHED = getBoolean("attached");

    @Override
    public boolean isAttached() {
        return get(ATTACHED);
    }

    @Override
    public void setAttached(boolean attached) {
        set(ATTACHED, attached);
    }
}
