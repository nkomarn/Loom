package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.BooleanProperty;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public abstract class CraftEndPortalFrame extends CraftBlockData implements EndPortalFrame {

    private static final BooleanProperty EYE = getBoolean("eye");

    @Override
    public boolean hasEye() {
        return get(EYE);
    }

    @Override
    public void setEye(boolean eye) {
        set(EYE, eye);
    }
}
