package org.bukkit.craftbukkit;

import net.minecraft.world.RayTraceContext;
import org.bukkit.FluidCollisionMode;

public final class CraftFluidCollisionMode {

    private CraftFluidCollisionMode() {}

    public static RayTraceContext.FluidHandling toNMS(FluidCollisionMode fluidCollisionMode) {
        if (fluidCollisionMode == null) return null;

        switch (fluidCollisionMode) {
            case ALWAYS:
                return RayTraceContext.FluidHandling.ANY;
            case SOURCE_ONLY:
                return RayTraceContext.FluidHandling.SOURCE_ONLY;
            case NEVER:
                return RayTraceContext.FluidHandling.NONE;
            default:
                return null;
        }
    }
}
