package org.bukkit;

import net.minecraft.util.registry.Registry;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Assert;
import org.junit.Test;

public class BiomeTest extends AbstractTestingBase {

    @Test
    public void testBukkitToMinecraft() {
        for (Biome biome : Biome.values()) {
            Assert.assertNotNull("No NMS mapping for " + biome, CraftBlock.biomeToBiomeBase(biome));
        }
    }

    @Test
    public void testMinecraftToBukkit() {
        for (net.minecraft.world.biome.Biome biome : Registry.BIOME) {
            Assert.assertNotNull("No Bukkit mapping for " + biome, CraftBlock.biomeBaseToBiome(biome));
        }
    }
}
