package org.bukkit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import com.google.common.collect.HashMultiset;
import net.minecraft.stat.StatType;
import net.minecraft.util.registry.Registry;
import org.bukkit.craftbukkit.CraftStatistic;
import org.bukkit.entity.EntityType;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Test;

public class StatisticsAndAchievementsTest extends AbstractTestingBase {

    @Test
    @SuppressWarnings("unchecked")
    public void verifyEntityMapping() throws Throwable {
        for (Statistic statistic : Statistic.values()) {
            if (statistic.getType() == Statistic.Type.ENTITY) {
                for (EntityType entity : EntityType.values()) {
                    if (entity.getName() != null) {
                        assertNotNull(statistic + " missing for " + entity, CraftStatistic.getEntityStatistic(statistic, entity));
                    }
                }
            }
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void verifyStatisticMapping() throws Throwable {
        HashMultiset<Statistic> statistics = HashMultiset.create();
        for (StatType wrapper : Registry.STAT_TYPE) {
            for (Object child : wrapper.getRegistry()) {
                net.minecraft.stat.Stat<?> statistic = wrapper.getOrCreateStat(child);
                String message = String.format("org.bukkit.Statistic is missing: '%s'", statistic);

                Statistic subject = CraftStatistic.getBukkitStatistic(statistic);
                assertThat(message, subject, is(not(nullValue())));

                if (wrapper.getRegistry() == Registry.BLOCK || wrapper.getRegistry() == Registry.ITEM) {
                    assertNotNull("Material type map missing for " + wrapper.getRegistry().getKey(child), CraftStatistic.getMaterialFromStatistic(statistic));
                } else if (wrapper.getRegistry() == Registry.ENTITY_TYPE) {
                    assertNotNull("Entity type map missing for " + net.minecraft.entity.EntityType.getId((net.minecraft.entity.EntityType<?>) child), CraftStatistic.getEntityTypeFromStatistic((net.minecraft.stat.Stat<net.minecraft.entity.EntityType<?>>) statistic));
                }

                statistics.add(subject);
            }
        }

        for (Statistic statistic : Statistic.values()) {
            String message = String.format("org.bukkit.Statistic.%s does not have a corresponding minecraft statistic", statistic.name());
            assertThat(message, statistics.remove(statistic, statistics.count(statistic)), is(greaterThan(0)));
        }
    }
}
