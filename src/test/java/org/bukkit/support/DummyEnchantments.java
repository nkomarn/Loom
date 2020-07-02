package org.bukkit.support;

import net.minecraft.enchantment.Enchantments;

public class DummyEnchantments {
    static {
        Enchantments.SHARPNESS.getClass();
        org.bukkit.enchantments.Enchantment.stopAcceptingRegistrations();
    }

    public static void setup() {}
}
