package org.bukkit.craftbukkit.entity;

import net.minecraft.entity.mob.ZoglinEntity;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zoglin;

public class CraftZoglin extends CraftMonster implements Zoglin {

    public CraftZoglin(CraftServer server, ZoglinEntity entity) {
        super(server, entity);
    }

    @Override
    public ZoglinEntity getHandle() {
        return (ZoglinEntity) entity;
    }

    @Override
    public String toString() {
        return "CraftZoglin";
    }

    @Override
    public EntityType getType() {
        return EntityType.ZOGLIN;
    }

    @Override
    public boolean isBaby() {
        getHandle().isBaby();
    }

    @Override
    public void setBaby(boolean baby) {
        getHandle().setBaby(baby);
    }
}
