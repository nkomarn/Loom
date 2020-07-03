package org.bukkit.craftbukkit.attribute;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Assert;
import org.junit.Test;

public class AttributeTest extends AbstractTestingBase {

    @Test
    public void testToBukkit() {
        for (Identifier nms : Registry.ATTRIBUTE.getIds()) {
            Attribute bukkit = CraftAttributeMap.fromMinecraft(nms.toString());

            Assert.assertNotNull(nms.toString(), bukkit);
        }
    }

    @Test
    public void testToNMS() {
        for (Attribute attribute : Attribute.values()) {
            EntityAttribute nms = CraftAttributeMap.toMinecraft(attribute);

            Assert.assertNotNull(attribute.name(), nms);
        }
    }
}
