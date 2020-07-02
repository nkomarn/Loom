package org.bukkit.block.banner;

import junit.framework.Assert;
import net.minecraft.block.entity.BannerPattern;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Test;

public class PatternTypeTest extends AbstractTestingBase {

    @Test
    public void testToBukkit() {
        for (BannerPattern nms : BannerPattern.values()) {
            PatternType bukkit = PatternType.getByIdentifier(nms.getId());

            Assert.assertNotNull("No Bukkit banner for " + nms + " " + nms.getId(), bukkit);
        }
    }

    @Test
    public void testToNMS() {
        for (PatternType bukkit : PatternType.values()) {
            BannerPattern found = null;
            for (BannerPattern nms : BannerPattern.values()) {
                if (bukkit.getIdentifier().equals(nms.getId())) {
                    found = nms;
                    break;
                }
            }

            Assert.assertNotNull("No NMS banner for " + bukkit + " " + bukkit.getIdentifier(), found);
        }
    }
}
