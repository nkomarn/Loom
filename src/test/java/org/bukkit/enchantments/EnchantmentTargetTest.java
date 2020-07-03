package org.bukkit.enchantments;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.bukkit.Material;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;
import org.junit.Assert;
import org.junit.Test;

public class EnchantmentTargetTest {

    @Test
    public void test() {
        for (net.minecraft.enchantment.EnchantmentTarget nmsSlot : net.minecraft.enchantment.EnchantmentTarget.values()) {
            EnchantmentTarget bukkitTarget;
            switch (nmsSlot) {
                case ARMOR_CHEST:
                    bukkitTarget = EnchantmentTarget.ARMOR_TORSO;
                    break;
                case DIGGER:
                    bukkitTarget = EnchantmentTarget.TOOL;
                    break;
                default:
                    bukkitTarget = EnchantmentTarget.valueOf(nmsSlot.name());
                    break;
            }

            Assert.assertNotNull("No bukkit target for slot " + nmsSlot, bukkitTarget);

            for (Item item : Registry.ITEM) {
                Material material = CraftMagicNumbers.getMaterial(item);

                boolean nms = nmsSlot.isAcceptableItem(item);
                boolean bukkit = bukkitTarget.includes(material);

                Assert.assertEquals("Slot mismatch for " + bukkitTarget + " and " + material, nms, bukkit);
            }
        }
    }
}
