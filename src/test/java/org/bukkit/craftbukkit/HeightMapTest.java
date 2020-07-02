package org.bukkit.craftbukkit;

import net.minecraft.world.Heightmap;
import org.bukkit.HeightMap;
import org.junit.Assert;
import org.junit.Test;

public class HeightMapTest {

    @Test
    public void heightMapConversionFromNMSToBukkitShouldNotThrowExceptio() {
        for (Heightmap.Type nmsHeightMapType : Heightmap.Type.values()) {
            Assert.assertNotNull("fromNMS", CraftHeightMap.fromNMS(nmsHeightMapType));
        }
    }

    @Test
    public void heightMapConversionFromBukkitToNMSShouldNotThrowExceptio() {
        for (HeightMap bukkitHeightMap : HeightMap.values()) {
            Assert.assertNotNull("toNMS", CraftHeightMap.toNMS(bukkitHeightMap));
        }
    }
}
