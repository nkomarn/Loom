package org.bukkit;

import static org.junit.Assert.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.bukkit.craftbukkit.util.CraftChatMessage;
import org.junit.Assert;
import org.junit.Test;

public class ChatTest {

    @Test
    public void testColors() {
        for (ChatColor color : ChatColor.values()) {
            Assert.assertNotNull(CraftChatMessage.getColor(color));
            Assert.assertEquals(color, CraftChatMessage.getColor(CraftChatMessage.getColor(color)));
        }

        for (Formatting format : Formatting.values()) {
            Assert.assertNotNull(CraftChatMessage.getColor(format));
            Assert.assertEquals(format, CraftChatMessage.getColor(CraftChatMessage.getColor(format)));
        }
    }

    @Test
    public void testURLJsonConversion() {
        Text[] components;
        components = CraftChatMessage.fromString("https://spigotmc.org/test Test Message");
        assertEquals("{\"extra\":[{\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://spigotmc.org/test\"},\"text\":\"https://spigotmc.org/test\"},{\"text\":\" Test Message\"}],\"text\":\"\"}",
                CraftChatMessage.toJSON(components[0]));

        components = CraftChatMessage.fromString("123 " + ChatColor.GOLD + "https://spigotmc.org " + ChatColor.BOLD + "test");
        assertEquals("{\"extra\":[{\"text\":\"123 \"},{\"color\":\"gold\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://spigotmc.org\"},\"text\":\"https://spigotmc.org\"},{\"color\":\"gold\",\"text\":\" \"},{\"bold\":true,\"color\":\"gold\",\"text\":\"test\"}],\"text\":\"\"}",
                CraftChatMessage.toJSON(components[0]));

        components = CraftChatMessage.fromString("multiCase http://SpigotMC.ORg/SpOngeBobMeEMeGoESHeRE");
        assertEquals("{\"extra\":[{\"text\":\"multiCase \"},{\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://SpigotMC.ORg/SpOngeBobMeEMeGoESHeRE\"},\"text\":\"http://SpigotMC.ORg/SpOngeBobMeEMeGoESHeRE\"}],\"text\":\"\"}",
                CraftChatMessage.toJSON(components[0]));
    }
}
