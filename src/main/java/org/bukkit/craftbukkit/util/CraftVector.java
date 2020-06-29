package org.bukkit.craftbukkit.util;

import net.minecraft.util.math.Vec3d;

public final class CraftVector {

    private CraftVector() {
    }

    public static org.bukkit.util.Vector toBukkit(Vec3d nms) {
        return new org.bukkit.util.Vector(nms.x, nms.y, nms.z);
    }

    public static Vec3d toNMS(org.bukkit.util.Vector bukkit) {
        return new Vec3d(bukkit.getX(), bukkit.getY(), bukkit.getZ());
    }
}
