package org.bukkit.entity;

import net.minecraft.entity.boss.dragon.phase.PhaseType;
import org.bukkit.craftbukkit.entity.CraftEnderDragon;
import org.junit.Assert;
import org.junit.Test;

public class EnderDragonPhaseTest {

    @Test
    public void testNotNull() {
        for (EnderDragon.Phase phase : EnderDragon.Phase.values()) {
            PhaseType dragonControllerPhase = CraftEnderDragon.getMinecraftPhase(phase);
            Assert.assertNotNull(phase.name(), dragonControllerPhase);
            Assert.assertNotNull(phase.name(), CraftEnderDragon.getBukkitPhase(dragonControllerPhase));
        }
    }

    @Test
    public void testBukkitToMinecraft() {
        Assert.assertEquals("CIRCLING", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.CIRCLING), PhaseType.HOLDING_PATTERN);
        Assert.assertEquals("STRAFING", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.STRAFING), PhaseType.STRAFE_PLAYER);
        Assert.assertEquals("FLY_TO_PORTAL", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.FLY_TO_PORTAL), PhaseType.LANDING_APPROACH);
        Assert.assertEquals("LAND_ON_PORTAL", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.LAND_ON_PORTAL), PhaseType.LANDING);
        Assert.assertEquals("LEAVE_PORTAL", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.LEAVE_PORTAL), PhaseType.TAKEOFF);
        Assert.assertEquals("BREATH_ATTACK", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.BREATH_ATTACK), PhaseType.SITTING_FLAMING);
        Assert.assertEquals("SEARCH_FOR_BREATH_ATTACK_TARGET", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.SEARCH_FOR_BREATH_ATTACK_TARGET), PhaseType.SITTING_SCANNING);
        Assert.assertEquals("ROAR_BEFORE_ATTACK", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.ROAR_BEFORE_ATTACK), PhaseType.SITTING_ATTACKING);
        Assert.assertEquals("CHARGE_PLAYER", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.CHARGE_PLAYER), PhaseType.CHARGING_PLAYER);
        Assert.assertEquals("DYING", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.DYING), PhaseType.DYING);
        Assert.assertEquals("HOVER", CraftEnderDragon.getMinecraftPhase(EnderDragon.Phase.HOVER), PhaseType.HOVER);
    }

    @Test
    public void testMinecraftToBukkit() {
        Assert.assertEquals("CIRCLING", CraftEnderDragon.getBukkitPhase(PhaseType.HOLDING_PATTERN), EnderDragon.Phase.CIRCLING);
        Assert.assertEquals("STRAFING", CraftEnderDragon.getBukkitPhase(PhaseType.STRAFE_PLAYER), EnderDragon.Phase.STRAFING);
        Assert.assertEquals("FLY_TO_PORTAL", CraftEnderDragon.getBukkitPhase(PhaseType.LANDING_APPROACH), EnderDragon.Phase.FLY_TO_PORTAL);
        Assert.assertEquals("LAND_ON_PORTAL", CraftEnderDragon.getBukkitPhase(PhaseType.LANDING), EnderDragon.Phase.LAND_ON_PORTAL);
        Assert.assertEquals("LEAVE_PORTAL", CraftEnderDragon.getBukkitPhase(PhaseType.TAKEOFF), EnderDragon.Phase.LEAVE_PORTAL);
        Assert.assertEquals("BREATH_ATTACK", CraftEnderDragon.getBukkitPhase(PhaseType.SITTING_FLAMING), EnderDragon.Phase.BREATH_ATTACK);
        Assert.assertEquals("SEARCH_FOR_BREATH_ATTACK_TARGET", CraftEnderDragon.getBukkitPhase(PhaseType.SITTING_SCANNING), EnderDragon.Phase.SEARCH_FOR_BREATH_ATTACK_TARGET);
        Assert.assertEquals("ROAR_BEFORE_ATTACK", CraftEnderDragon.getBukkitPhase(PhaseType.SITTING_ATTACKING), EnderDragon.Phase.ROAR_BEFORE_ATTACK);
        Assert.assertEquals("CHARGE_PLAYER", CraftEnderDragon.getBukkitPhase(PhaseType.CHARGING_PLAYER), EnderDragon.Phase.CHARGE_PLAYER);
        Assert.assertEquals("DYING", CraftEnderDragon.getBukkitPhase(PhaseType.DYING), EnderDragon.Phase.DYING);
        Assert.assertEquals("HOVER", CraftEnderDragon.getBukkitPhase(PhaseType.HOVER), EnderDragon.Phase.HOVER);
    }
}
