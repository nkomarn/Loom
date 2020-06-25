/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.state.property.BooleanProperty;

public final class CraftJukeBox extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Jukebox {

    public CraftJukeBox() {
        super();
    }

    public CraftJukeBox(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftJukebox

    private static final BooleanProperty HAS_RECORD = getBoolean(JukeboxBlock.class, "has_record");

    @Override
    public boolean hasRecord() {
        return get(HAS_RECORD);
    }
}
