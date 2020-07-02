package org.bukkit.potion;

import static org.junit.Assert.*;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Test;

public class PotionTest extends AbstractTestingBase {
    @Test
    public void testEffectCompleteness() throws Throwable {
        Map<PotionType, String> effects = new EnumMap(PotionType.class);
        for (Object reg : Registry.POTION) {
            List<StatusEffectInstance> eff = ((Potion) reg).getEffects();
            if (eff.size() != 1) continue;
            int id = StatusEffect.getRawId(eff.get(0).getEffectType());
            PotionEffectType type = PotionEffectType.getById(id);
            assertNotNull(String.valueOf(id), PotionEffectType.getById(id));

            PotionType enumType = PotionType.getByEffect(type);
            assertNotNull(type.getName(), enumType);

            effects.put(enumType, enumType.name());
        }

        assertEquals(effects.entrySet().size(), PotionType.values().length - /* PotionTypes with no/shared Effects */ 6);
    }

    @Test
    public void testEffectType() {
        for (StatusEffect nms : Registry.STATUS_EFFECT) {
            Identifier key = Registry.STATUS_EFFECT.getId(nms);

            int id = StatusEffect.getRawId(nms);
            PotionEffectType bukkit = PotionEffectType.getById(id);

            assertNotNull("No Bukkit type for " + key, bukkit);
            assertFalse("No name for " + key, bukkit.getName().contains("UNKNOWN"));

            PotionEffectType byName = PotionEffectType.getByName(bukkit.getName());
            assertEquals("Same type not returned by name " + key, bukkit, byName);
        }
    }
}
