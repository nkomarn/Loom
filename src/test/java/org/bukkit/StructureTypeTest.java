package org.bukkit;

import java.util.Map;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This test makes sure that Bukkit always has Minecraft structure types up to
 * date.
 */
public class StructureTypeTest extends AbstractTestingBase {

    private static Map<String, StructureType> structures;

    @BeforeClass
    public static void setUp() {
        structures = StructureType.getStructureTypes();
    }

    @Test
    public void testMinecraftToBukkit() {
        for (Identifier key : Registry.STRUCTURE_FEATURE.keySet()) {
            Assert.assertNotNull(key.getKey(), structures.get(key.getKey()));
        }
    }

    @Test
    public void testBukkit() {
        for (Map.Entry<String, StructureType> entry : structures.entrySet()) {
            Assert.assertNotNull(entry.getKey(), StructureType.getStructureTypes().get(entry.getKey()));
            Assert.assertNotNull(entry.getValue().getName(), StructureType.getStructureTypes().get(entry.getValue().getName()));
        }
    }

    @Test
    public void testBukkitToMinecraft() {
        for (Map.Entry<String, StructureType> entry : structures.entrySet()) {
            Assert.assertNotNull(entry.getKey(), Registry.STRUCTURE_FEATURE.get(new Identifier(entry.getKey())));
            Assert.assertNotNull(entry.getValue().getName(), Registry.STRUCTURE_FEATURE.get(new Identifier(entry.getValue().getName())));
        }
    }
}
