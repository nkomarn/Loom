package org.bukkit.craftbukkit.entity;

import net.minecraft.entity.mob.HoglinEntity;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Hoglin;

public class CraftHoglin extends CraftAnimals implements Hoglin {

    public CraftHoglin(CraftServer server, HoglinEntity entity) {
        super(server, entity);
    }

    @Override
    public HoglinEntity getHandle() {
        return (HoglinEntity) entity;
    }

    @Override
    public String toString() {
        return "CraftHoglin";
    }

    @Override
    public EntityType getType() {
        return EntityType.HOGLIN;
    }
}
