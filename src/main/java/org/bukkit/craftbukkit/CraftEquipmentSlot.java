package org.bukkit.craftbukkit;

import net.minecraft.entity.EquipmentSlot;

public class CraftEquipmentSlot {

    private static final EquipmentSlot[] slots = new EquipmentSlot[EquipmentSlot.values().length];
    private static final org.bukkit.inventory.EquipmentSlot[] enums = new org.bukkit.inventory.EquipmentSlot[org.bukkit.inventory.EquipmentSlot.values().length];

    static {
        set(org.bukkit.inventory.EquipmentSlot.HAND, EquipmentSlot.MAINHAND);
        set(org.bukkit.inventory.EquipmentSlot.OFF_HAND, EquipmentSlot.OFFHAND);
        set(org.bukkit.inventory.EquipmentSlot.FEET, EquipmentSlot.FEET);
        set(org.bukkit.inventory.EquipmentSlot.LEGS, EquipmentSlot.LEGS);
        set(org.bukkit.inventory.EquipmentSlot.CHEST, EquipmentSlot.CHEST);
        set(org.bukkit.inventory.EquipmentSlot.HEAD, EquipmentSlot.HEAD);
    }

    private static void set(org.bukkit.inventory.EquipmentSlot type, EquipmentSlot value) {
        slots[type.ordinal()] = value;
        enums[value.ordinal()] = type;
    }

    public static org.bukkit.inventory.EquipmentSlot getSlot(EquipmentSlot nms) {
        return enums[nms.ordinal()];
    }

    public static EquipmentSlot getNMS(org.bukkit.inventory.EquipmentSlot slot) {
        return slots[slot.ordinal()];
    }
}
