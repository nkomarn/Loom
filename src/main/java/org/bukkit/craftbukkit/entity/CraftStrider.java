package org.bukkit.craftbukkit.entity;

import com.google.common.base.Preconditions;
import net.minecraft.entity.passive.StriderEntity;
import org.bukkit.Material;
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

    @Override
    public boolean isShivering() {
        return getHandle().isCold();
    }

    @Override
    public void setShivering(boolean shivering) {
        getHandle().setCold(shivering);
    }

    @Override
    public boolean hasSaddle() {
        return getHandle().isSaddled();
    }

    @Override
    public void setSaddle(boolean saddled) {
        getHandle().saddledComponent.setSaddled(saddled);
    }

    @Override
    public int getBoostTicks() {
        return 0;
    }

    @Override
    public void setBoostTicks(int ticks) {
        Preconditions.checkArgument(ticks >= 0, (Object)"ticks must be >= 0");
        this.getHandle().saddledComponent.setBoostTicks(ticks);
    }

    @Override
    public int getCurrentBoostTicks() {
        return this.getHandle().saddledComponent.boosted ? this.getHandle().saddledComponent.field_23216 : 0;
    }

    @Override
    public void setCurrentBoostTicks(int ticks) {
        if (!this.getHandle().saddledComponent.boosted) {
            return;
        }
        final int max = this.getHandle().saddledComponent.currentBoostTime;
        Preconditions.checkArgument(ticks >= 0 && ticks <= max, "boost ticks must not exceed 0 or %d (inclusive)", max);
        this.getHandle().saddledComponent.field_23216 = ticks;
    }

    @Override
    public Material getSteerMaterial() {
        return Material.WARPED_FUNGUS_ON_A_STICK;
    }
}
