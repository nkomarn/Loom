package org.bukkit.entity.memory;

import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.util.dynamic.GlobalPos;
import net.minecraft.util.registry.Registry;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.memory.CraftMemoryKey;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Assert;
import org.junit.Test;

public class CraftMemoryKeyTest extends AbstractTestingBase {

    @Test
    public void shouldConvertBukkitHomeKeyToNMSRepresentation() {
        MemoryModuleType<GlobalPos> nmsHomeKey = CraftMemoryKey.fromMemoryKey(MemoryKey.HOME);
        Assert.assertEquals("MemoryModuleType should be HOME", MemoryModuleType.HOME, nmsHomeKey);
    }

    @Test
    public void shouldConvertBukkitJobSiteKeyToNMSRepresentation() {
        MemoryModuleType<GlobalPos> nmsHomeKey = CraftMemoryKey.fromMemoryKey(MemoryKey.JOB_SITE);
        Assert.assertEquals("MemoryModuleType should be JOB_SITE", MemoryModuleType.JOB_SITE, nmsHomeKey);
    }

    @Test
    public void shouldConvertBukkitMeetingPointKeyToNMSRepresentation() {
        MemoryModuleType<GlobalPos> nmsHomeKey = CraftMemoryKey.fromMemoryKey(MemoryKey.MEETING_POINT);
        Assert.assertEquals("MemoryModuleType should be MEETING_POINT", MemoryModuleType.MEETING_POINT, nmsHomeKey);
    }

    @Test
    public void shouldConvertNMSHomeKeyToBukkitRepresentation() {
        MemoryKey<Location> bukkitHomeKey = CraftMemoryKey.toMemoryKey(MemoryModuleType.HOME);
        Assert.assertEquals("MemoryModuleType should be HOME", MemoryKey.HOME, bukkitHomeKey);
    }

    @Test
    public void shouldConvertNMSJobSiteKeyToBukkitRepresentation() {
        MemoryKey<Location> bukkitJobSiteKey = CraftMemoryKey.toMemoryKey(MemoryModuleType.JOB_SITE);
        Assert.assertEquals("MemoryKey should be JOB_SITE", MemoryKey.JOB_SITE, bukkitJobSiteKey);
    }

    @Test
    public void shouldConvertNMSMeetingPointKeyToBukkitRepresentation() {
        MemoryKey<Location> bukkitHomeKey = CraftMemoryKey.toMemoryKey(MemoryModuleType.MEETING_POINT);
        Assert.assertEquals("MemoryKey should be MEETING_POINT", MemoryKey.MEETING_POINT, bukkitHomeKey);
    }

    @Test
    public void shouldReturnNullWhenBukkitRepresentationOfKeyisNotAvailable() {
        MemoryKey bukkitNoKey = CraftMemoryKey.toMemoryKey(MemoryModuleType.MOBS);
        Assert.assertNull("MemoryModuleType should be null", bukkitNoKey);
    }

    @Test
    public void shouldReturnNullWhenBukkitRepresentationOfKeyisNotAvailableAndSerializerIsNotPresent() {
        for (MemoryModuleType<?> memoryModuleType : Registry.MEMORY_MODULE_TYPE) {
            if (!memoryModuleType.getFactory().isPresent()) {
                MemoryKey bukkitNoKey = CraftMemoryKey.toMemoryKey(memoryModuleType);
                Assert.assertNull("MemoryModuleType should be null", bukkitNoKey);
            }
        }
    }

    @Test
    public void shouldReturnAnInstanceOfMemoryKeyWhenBukkitRepresentationOfKeyisAvailableAndSerializerIsPresent() {
        for (MemoryModuleType<?> memoryModuleType : Registry.MEMORY_MODULE_TYPE) {
            if (memoryModuleType.getFactory().isPresent()) {
                MemoryKey bukkitNoKey = CraftMemoryKey.toMemoryKey(memoryModuleType);
                Assert.assertNotNull("MemoryModuleType should not be null " + Registry.MEMORY_MODULE_TYPE.getKey(memoryModuleType), bukkitNoKey);
            }
        }
    }
}
