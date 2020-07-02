package org.bukkit.craftbukkit.entity;

import net.minecraft.entity.passive.StriderEntity;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Strider;

public class CraftStrider extends CraftAnimals implements Strider {

    public CraftStrider(CraftServer server, StriderEntity entity) {
        super(server, entity);
    }

    @Override
    public StriderEntity getHandle() {
        return (StriderEntity) entity;
    }

    @Override
    public String toString() {
        return "CraftStrider";
    }

    @Override
    public EntityType getType() {
        return EntityType.STRIDER;
    }
}
