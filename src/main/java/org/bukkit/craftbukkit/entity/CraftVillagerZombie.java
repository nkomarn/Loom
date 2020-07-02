package org.bukkit.craftbukkit.entity;

import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.UUID;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;

public class CraftVillagerZombie extends CraftZombie implements ZombieVillager {

    public CraftVillagerZombie(CraftServer server, ZombieVillagerEntity entity) {
        super(server, entity);
    }

    @Override
    public ZombieVillagerEntity getHandle() {
        return (ZombieVillagerEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftVillagerZombie";
    }

    @Override
    public EntityType getType() {
        return EntityType.ZOMBIE_VILLAGER;
    }

    @Override
    public Villager.Profession getVillagerProfession() {
        return Villager.Profession.valueOf(Registry.VILLAGER_PROFESSION.getKey(getHandle().getVillagerData().getProfession()).getKey().toUpperCase(Locale.ROOT));
    }

    @Override
    public void setVillagerProfession(Villager.Profession profession) {
        Validate.notNull(profession);
        getHandle().setVillagerData(getHandle().getVillagerData().withProfession(Registry.VILLAGER_PROFESSION.get(new Identifier(profession.name().toLowerCase(Locale.ROOT)))));
    }

    @Override
    public Villager.Type getVillagerType() {
        return Villager.Type.valueOf(Registry.VILLAGER_TYPE.getKey(getHandle().getVillagerData().getType()).getKey().toUpperCase(Locale.ROOT));
    }

    @Override
    public void setVillagerType(Villager.Type type) {
        Validate.notNull(type);
        getHandle().setVillagerData(getHandle().getVillagerData().withType(Registry.VILLAGER_TYPE.get(CraftNamespacedKey.toMinecraft(type.getKey()))));
    }

    @Override
    public boolean isConverting() {
        return getHandle().isConverting();
    }

    @Override
    public int getConversionTime() {
        Preconditions.checkState(isConverting(), "Entity not converting");

        return getHandle().conversionTime;
    }

    @Override
    public void setConversionTime(int time) {
        if (time < 0) {
            getHandle().conversionTime = -1;
            getHandle().getDataWatcher().set(EntityZombieVillager.CONVERTING, false);
            getHandle().persistent = false; // CraftBukkit - SPIGOT-4684 update persistence
            getHandle().conversionPlayer = null;
            getHandle().removeEffect(MobEffects.INCREASE_DAMAGE, org.bukkit.event.entity.EntityPotionEffectEvent.Cause.CONVERSION);
        } else {
            getHandle().startConversion((UUID) null, time);
        }
    }

    @Override
    public OfflinePlayer getConversionPlayer() {
        return (getHandle().conversionPlayer == null) ? null : Bukkit.getOfflinePlayer(getHandle().conversionPlayer);
    }

    @Override
    public void setConversionPlayer(OfflinePlayer conversionPlayer) {
        if (!this.isConverting()) return;
        getHandle().conversionPlayer = (conversionPlayer == null) ? null : conversionPlayer.getUniqueId();
    }
}
