package org.bukkit;

import net.minecraft.particle.ParticleType;
import net.minecraft.util.registry.Registry;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.CraftParticle;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Assert;
import org.junit.Test;

public class ParticleTest extends AbstractTestingBase {

    @Test
    public void verifyMapping() {
        for (Particle bukkit : Particle.values()) {
            Object data = null;
            if (bukkit.getDataType().equals(ItemStack.class)) {
                data = new ItemStack(Material.STONE);
            } else if (bukkit.getDataType() == MaterialData.class) {
                data = new MaterialData(Material.LEGACY_STONE);
            } else if (bukkit.getDataType() == Particle.DustOptions.class) {
                data = new Particle.DustOptions(Color.BLACK, 0);
            } else if (bukkit.getDataType() == BlockData.class) {
                data = CraftBlockData.newData(Material.STONE, "");
            }

            Assert.assertNotNull("Missing Bukkit->NMS particle mapping for " + bukkit, CraftParticle.toNMS(bukkit, data));
        }
        for (ParticleType nms : Registry.PARTICLE_TYPE) {
            Assert.assertNotNull("Missing NMS->Bukkit particle mapping for " + Registry.PARTICLE_TYPE.getKey(nms), CraftParticle.toBukkit(nms));
        }
    }
}
