package org.bukkit.craftbukkit.util;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.light.LightingProvider;
import net.minecraft.world.dimension.DimensionType;

import javax.annotation.Nullable;

public class DummyGeneratorAccess implements WorldAccess {

    public static final WorldAccess INSTANCE = new DummyGeneratorAccess();

    protected DummyGeneratorAccess() {
    }

    @Override
    public TickScheduler<Block> getBlockTickScheduler() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TickScheduler<Fluid> getFluidTickScheduler() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public World getWorld() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WorldProperties getLevelProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LocalDifficulty getLocalDifficulty(BlockPos blockPos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ChunkManager getChunkManager() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Random getRandom() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void playSound(@Nullable PlayerEntity playerEntity, BlockPos blockPos, SoundEvent soundEvent, SoundCategory soundCategory, float f, float g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addParticle(ParticleEffect effect, double d, double e, double f, double g, double h, double i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void syncWorldEvent(@Nullable PlayerEntity playerEntity, int i, BlockPos blockPos, int j) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public LightingProvider getLightingProvider() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WorldBorder getWorldBorder() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Nullable
    @Override
    public BlockEntity getBlockEntity(BlockPos arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BlockState getBlockState(BlockPos blockPos) {
        return null;
    }

    @Override
    public FluidState getFluidState(BlockPos blockPos) {
        return null;
    }

    @Override
    public List<Entity> getEntities(@Nullable Entity entity, Box box, @Nullable Predicate<? super Entity> predicate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T extends Entity> List<T> getEntities(Class<? extends T> class_, Box box, @Nullable Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<? extends PlayerEntity> getPlayers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setBlockState(BlockPos blockPos, BlockState blockState, int i, int j) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeBlock(BlockPos blockPos, boolean bl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean method_30093(BlockPos blockPos, boolean bl, @Nullable Entity entity, int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean testBlockState(BlockPos blockPos, Predicate<BlockState> predicate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Nullable
    @Override
    public Chunk getChunk(int x, int z, ChunkStatus chunkStatus, boolean bl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTopY(Heightmap.Type type, int i, int j) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getAmbientDarkness() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BiomeAccess getBiomeAccess() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Biome getGeneratorStoredBiome(int i, int j, int k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isClient() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getSeaLevel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DimensionType getDimension() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
