package org.bukkit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.bukkit.craftbukkit.CraftSound;
import org.bukkit.support.AbstractTestingBase;
import org.junit.Test;

public class SoundTest extends AbstractTestingBase {

    @Test
    public void testGetSound() {
        for (Sound sound : Sound.values()) {
            assertThat(sound.name(), CraftSound.getSound(sound), is(not(nullValue())));
        }
    }

    @Test
    public void testReverse() {
        for (Identifier effect : Registry.SOUND_EVENT.getIds()) {
            assertNotNull(effect + "", Sound.valueOf(effect.getPath().replace('.', '_').toUpperCase(java.util.Locale.ENGLISH)));
        }
    }

    @Test
    public void testCategory() {
        for (SoundCategory category : SoundCategory.values()) {

            assertNotNull(category + "", net.minecraft.sound.SoundCategory.valueOf(category.name()));
        }
    }

    @Test
    public void testCategoryReverse() {
        for (net.minecraft.sound.SoundCategory category : net.minecraft.sound.SoundCategory.values()) {
            assertNotNull(category + "", SoundCategory.valueOf(category.name()));
        }
    }
}
