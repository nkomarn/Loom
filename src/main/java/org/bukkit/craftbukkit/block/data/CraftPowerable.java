package org.bukkit.craftbukkit.block.data;

import net.minecraft.state.property.BooleanProperty;
import org.bukkit.block.data.Powerable;

public abstract class CraftPowerable extends CraftBlockData implements Powerable {

    private static final BooleanProperty POWERED = getBoolean("powered");

    @Override
    public boolean isPowered() {
        return get(POWERED);
    }

    @Override
    public void setPowered(boolean powered) {
        set(POWERED, powered);
    }
}
