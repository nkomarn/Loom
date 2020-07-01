package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.LivingEntity;

public abstract class CraftComplexLivingEntity extends CraftLivingEntity implements ComplexLivingEntity {
    public CraftComplexLivingEntity(CraftServer server, LivingEntity entity) {
        super(server, entity);
    }

    @Override
    public LivingEntity getHandle() {
        return (LivingEntity) entity;
    }

    @Override
    public String toString() {
        return "CraftComplexLivingEntity";
    }
}
