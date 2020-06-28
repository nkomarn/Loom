package org.bukkit.craftbukkit.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.bukkit.craftbukkit.block.CraftBlockState;

public class BlockStateListPopulator extends DummyGeneratorAccess {
    private final World world;
    private final LinkedHashMap<BlockPos, CraftBlockState> list;

    public BlockStateListPopulator(World world) {
        this(world, new LinkedHashMap<>());
    }

    public BlockStateListPopulator(World world, LinkedHashMap<BlockPos, CraftBlockState> list) {
        this.world = world;
        this.list = list;
    }

    @Override
    public BlockState getBlockState(BlockPos blockPos) {
        CraftBlockState state = list.get(blockPos);
        return (state != null) ? state.getHandle() : world.getBlockState(blockPos);
    }

    @Override
    public FluidState getFluidState(BlockPos blockPos) {
        CraftBlockState state = list.get(blockPos);
        return (state != null) ? state.getHandle().getFluidState() : world.getFluidState(blockPos);
    }

    @Override
    public boolean setBlockState(BlockPos blockpos, BlockState blockstate, int flag) {
        CraftBlockState state = CraftBlockState.getBlockState(world, blockpos, flag);
        state.setData(blockstate);
        list.put(blockpos, state);
        return true;
    }

    public void updateList() {
        for (CraftBlockState state : list.values()) {
            state.update(true);
        }
    }

    public Set<BlockPos> getBlocks() {
        return list.keySet();
    }

    public List<CraftBlockState> getList() {
        return new ArrayList<>(list.values());
    }

    public World getWorld() {
        return world;
    }
}
