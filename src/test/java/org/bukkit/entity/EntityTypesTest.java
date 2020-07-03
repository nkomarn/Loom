package org.bukkit.entity;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Assert;
import org.junit.Test;

public class EntityTypesTest extends AbstractTestingBase {

    @Test
    public void testMaps() {
        Set<EntityType> allBukkit = Arrays.stream(EntityType.values()).filter((b) -> b.getName() != null).collect(Collectors.toSet());

        for (net.minecraft.entity.EntityType<?> nms : Registry.ENTITY_TYPE) {
            Identifier key = net.minecraft.entity.EntityType.getId(nms);

            EntityType bukkit = EntityType.fromName(key.getPath());
            Assert.assertNotNull("Missing nms->bukkit " + key, bukkit);

            Assert.assertTrue("Duplicate entity nms->" + bukkit, allBukkit.remove(bukkit));
        }

        Assert.assertTrue("Unmapped bukkit entities " + allBukkit, allBukkit.isEmpty());
    }
}
