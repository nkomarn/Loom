package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.BooleanProperty;
import org.bukkit.block.data.type.Jukebox;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public abstract class CraftJukebox extends CraftBlockData implements Jukebox {

    private static final BooleanProperty HAS_RECORD = getBoolean("has_record");

    @Override
    public boolean hasRecord() {
        return get(HAS_RECORD);
    }
}
