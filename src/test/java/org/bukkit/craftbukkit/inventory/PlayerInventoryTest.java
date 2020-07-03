package org.bukkit.craftbukkit.inventory;

import static org.junit.Assert.*;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Test;

public class PlayerInventoryTest extends AbstractTestingBase {

    @Test
    public void testCanHold() throws Exception {
        ItemStack itemStackApple = new ItemStack(Items.APPLE);
        ItemStack itemStack1Coal = new ItemStack(Items.COAL);
        ItemStack itemStack32Coal = new ItemStack(Items.COAL, 32);
        ItemStack itemStack63Coal = new ItemStack(Items.COAL, 63);
        ItemStack itemStack64Coal = new ItemStack(Items.COAL, 64);

        // keep one slot empty
        PlayerInventory inventory = new PlayerInventory(null);
        for (int i = 0; i < inventory.main.size() - 1; i++) {
            inventory.setStack(i, itemStackApple);
        }

        // one slot empty
        assertEquals(1, inventory.canHold(itemStack1Coal));
        assertEquals(32, inventory.canHold(itemStack32Coal));
        assertEquals(64, inventory.canHold(itemStack64Coal));

        // no free space with a stack of the item to check in the inventory
        inventory.setStack(inventory.main.size() - 1, itemStack64Coal);

        assertEquals(0, inventory.canHold(itemStack1Coal));
        assertEquals(0, inventory.canHold(itemStack32Coal));
        assertEquals(0, inventory.canHold(itemStack64Coal));

        // no free space without a stack of the item to check in the inventory
        inventory.setStack(inventory.main.size() - 1, itemStackApple);

        assertEquals(0, inventory.canHold(itemStack1Coal));
        assertEquals(0, inventory.canHold(itemStack32Coal));
        assertEquals(0, inventory.canHold(itemStack64Coal));

        // free space for 32 items in one slot
        inventory.setStack(inventory.main.size() - 1, itemStack32Coal);

        assertEquals(1, inventory.canHold(itemStack1Coal));
        assertEquals(32, inventory.canHold(itemStack32Coal));
        assertEquals(32, inventory.canHold(itemStack64Coal));

        // free space for 1 item in two slots
        inventory.setStack(inventory.main.size() - 1, itemStack63Coal);
        inventory.setStack(inventory.main.size() - 2, itemStack63Coal);

        assertEquals(1, inventory.canHold(itemStack1Coal));
        assertEquals(2, inventory.canHold(itemStack32Coal));
        assertEquals(2, inventory.canHold(itemStack64Coal));

        // free space for 32 items in non-empty off-hand slot
        inventory.setStack(inventory.main.size() - 1, itemStackApple);
        inventory.setStack(inventory.main.size() - 2, itemStackApple);
        inventory.setStack(inventory.main.size() + inventory.armor.size(), itemStack32Coal);

        assertEquals(1, inventory.canHold(itemStack1Coal));
        assertEquals(32, inventory.canHold(itemStack32Coal));
        assertEquals(32, inventory.canHold(itemStack64Coal));

        // free space for 1 item in non-empty off-hand slot and another slot
        inventory.setStack(inventory.main.size() - 1, itemStack63Coal);
        inventory.setStack(inventory.main.size() + inventory.armor.size(), itemStack63Coal);

        assertEquals(1, inventory.canHold(itemStack1Coal));
        assertEquals(2, inventory.canHold(itemStack32Coal));
        assertEquals(2, inventory.canHold(itemStack64Coal));
    }
}
