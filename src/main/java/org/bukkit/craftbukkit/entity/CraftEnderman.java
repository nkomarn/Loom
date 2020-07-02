package org.bukkit.craftbukkit.entity;

import net.minecraft.entity.mob.EndermanEntity;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;

public class CraftEnderman extends CraftMonster implements Enderman {
    public CraftEnderman(CraftServer server, EndermanEntity entity) {
        super(server, entity);
    }

    @Override
    public MaterialData getCarriedMaterial() {
        IBlockData blockData = getHandle().getCarried();
        return (blockData == null) ? Material.AIR.getNewData((byte) 0) : CraftMagicNumbers.getMaterial(blockData);
    }

    @Override
    public BlockData getCarriedBlock() {
        IBlockData blockData = getHandle().getCarried();
        return (blockData == null) ? null : CraftBlockData.fromData(blockData);
    }

    @Override
    public void setCarriedMaterial(MaterialData data) {
        getHandle().setCarried(CraftMagicNumbers.getBlock(data));
    }

    @Override
    public void setCarriedBlock(BlockData blockData) {
        getHandle().setCarried(blockData == null ? null : ((CraftBlockData) blockData).getState());
    }

    @Override
    public EndermanEntity getHandle() {
        return (EndermanEntity) entity;
    }

    @Override
    public String toString() {
        return "CraftEnderman";
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDERMAN;
    }
}
