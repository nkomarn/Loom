package org.bukkit.craftbukkit.entity;

import com.google.common.base.Preconditions;
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

    @Override
    public boolean isImmuneToZombification() {
        return getHandle().isImmuneToZombification();
    }

    @Override
    public void setImmuneToZombification(boolean immune) {
        getHandle().setImmuneToZombification(immune);
    }

    @Override
    public boolean isAbleToBeHunted() {
        return getHandle().cannotBeHunted;
    }

    @Override
    public void setIsAbleToBeHunted(boolean flag) {
        getHandle().cannotBeHunted = flag;
    }

    @Override
    public int getConversionTime() {
        Preconditions.checkState(this.isConverting(), "Entity not converting");
        return this.getHandle().timeInOverworld;
    }

    @Override
    public void setConversionTime(int time) {
        if (time < 0) {
            this.getHandle().timeInOverworld = -1;
            this.getHandle().setImmuneToZombification(false);
        }
        else {
            this.getHandle().timeInOverworld = time;
        }
    }

    @Override
    public boolean isConverting() {
        return getHandle().canConvert();
    }
}
