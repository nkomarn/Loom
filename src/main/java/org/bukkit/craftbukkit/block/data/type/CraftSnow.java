package org.bukkit.craftbukkit.block.data.type;

import net.minecraft.state.property.IntProperty;
import org.bukkit.block.data.type.Snow;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public class CraftSnow extends CraftBlockData implements Snow {

    private static final IntProperty LAYERS = getInteger("layers");

    @Override
    public int getLayers() {
        return get(LAYERS);
    }

    @Override
    public void setLayers(int layers) {
        set(LAYERS, layers);
    }

    @Override
    public int getMinimumLayers() {
        return getMin(LAYERS);
    }

    @Override
    public int getMaximumLayers() {
        return getMax(LAYERS);
    }
}
