package org.bukkit.entity;

import net.minecraft.entity.passive.PandaEntity;
import org.bukkit.craftbukkit.entity.CraftPanda;
import org.junit.Assert;
import org.junit.Test;

public class PandaGeneTest {

    @Test
    public void testBukkit() {
        for (Panda.Gene gene : Panda.Gene.values()) {
            PandaEntity.Gene nms = CraftPanda.toNms(gene);

            Assert.assertNotNull("NMS gene null for " + gene, nms);
            Assert.assertEquals("Recessive status did not match " + gene, gene.isRecessive(), nms.isRecessive());
            Assert.assertEquals("Gene did not convert back " + gene, gene, CraftPanda.fromNms(nms));
        }
    }

    @Test
    public void testNMS() {
        for (PandaEntity.Gene gene : PandaEntity.Gene.values()) {
            Panda.Gene bukkit = CraftPanda.fromNms(gene);

            Assert.assertNotNull("Bukkit gene null for " + gene, bukkit);
            Assert.assertEquals("Recessive status did not match " + gene, gene.isRecessive(), bukkit.isRecessive());
            Assert.assertEquals("Gene did not convert back " + gene, gene, CraftPanda.toNms(bukkit));
        }
    }
}
