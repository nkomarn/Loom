package org.bukkit.craftbukkit;

import net.minecraft.world.Heightmap;
import org.bukkit.HeightMap;

final class CraftHeightMap {

    private CraftHeightMap() {
    }

    public static Heightmap.Type toNMS(HeightMap bukkitHeightMap) {
        switch (bukkitHeightMap) {
            case MOTION_BLOCKING_NO_LEAVES:
                return Heightmap.Type.MOTION_BLOCKING_NO_LEAVES;
            case OCEAN_FLOOR:
                return Heightmap.Type.OCEAN_FLOOR;
            case OCEAN_FLOOR_WG:
                return Heightmap.Type.OCEAN_FLOOR_WG;
            case WORLD_SURFACE:
                return Heightmap.Type.WORLD_SURFACE;
            case WORLD_SURFACE_WG:
                return Heightmap.Type.WORLD_SURFACE_WG;
            case MOTION_BLOCKING:
                return Heightmap.Type.MOTION_BLOCKING;
            default:
                throw new EnumConstantNotPresentException(Heightmap.Type.class, bukkitHeightMap.name());
        }
    }

    public static HeightMap fromNMS(Heightmap.Type nmsHeightMapType) {
        switch (nmsHeightMapType) {
            case WORLD_SURFACE_WG:
                return HeightMap.WORLD_SURFACE_WG;
            case WORLD_SURFACE:
                return HeightMap.WORLD_SURFACE;
            case OCEAN_FLOOR_WG:
                return HeightMap.OCEAN_FLOOR_WG;
            case OCEAN_FLOOR:
                return HeightMap.OCEAN_FLOOR;
            case MOTION_BLOCKING_NO_LEAVES:
                return HeightMap.MOTION_BLOCKING_NO_LEAVES;
            case MOTION_BLOCKING:
                return HeightMap.MOTION_BLOCKING;
            default:
                throw new EnumConstantNotPresentException(HeightMap.class, nmsHeightMapType.name());
        }
    }
}
