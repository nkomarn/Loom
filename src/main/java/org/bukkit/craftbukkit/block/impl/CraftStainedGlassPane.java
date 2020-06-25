/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.StainedGlassPaneBlock;
import net.minecraft.state.property.BooleanProperty;

public final class CraftStainedGlassPane extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.GlassPane, org.bukkit.block.data.MultipleFacing, org.bukkit.block.data.Waterlogged {

    public CraftStainedGlassPane() {
        super();
    }

    public CraftStainedGlassPane(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.CraftMultipleFacing

    private static final BooleanProperty[] FACES = new BooleanProperty[]{
            getBoolean(StainedGlassPaneBlock.class, "north", true), getBoolean(StainedGlassPaneBlock.class, "east", true),
            getBoolean(StainedGlassPaneBlock.class, "south", true), getBoolean(StainedGlassPaneBlock.class, "west", true),
            getBoolean(StainedGlassPaneBlock.class, "up", true), getBoolean(StainedGlassPaneBlock.class, "down", true)
    };

    @Override
    public boolean hasFace(org.bukkit.block.BlockFace face) {
        BooleanProperty state = FACES[face.ordinal()];
        if (state == null) {
            throw new IllegalArgumentException("Non-allowed face " + face + ". Check MultipleFacing.getAllowedFaces.");
        }
        return get(state);
    }

    @Override
    public void setFace(org.bukkit.block.BlockFace face, boolean has) {
        BooleanProperty state = FACES[face.ordinal()];
        if (state == null) {
            throw new IllegalArgumentException("Non-allowed face " + face + ". Check MultipleFacing.getAllowedFaces.");
        }
        set(state, has);
    }

    @Override
    public java.util.Set<org.bukkit.block.BlockFace> getFaces() {
        com.google.common.collect.ImmutableSet.Builder<org.bukkit.block.BlockFace> faces =
                com.google.common.collect.ImmutableSet.builder();

        for (int i = 0; i < FACES.length; i++) {
            if (FACES[i] != null && get(FACES[i])) {
                faces.add(org.bukkit.block.BlockFace.values()[i]);
            }
        }

        return faces.build();
    }

    @Override
    public java.util.Set<org.bukkit.block.BlockFace> getAllowedFaces() {
        com.google.common.collect.ImmutableSet.Builder<org.bukkit.block.BlockFace> faces =
                com.google.common.collect.ImmutableSet.builder();

        for (int i = 0; i < FACES.length; i++) {
            if (FACES[i] != null) {
                faces.add(org.bukkit.block.BlockFace.values()[i]);
            }
        }

        return faces.build();
    }

    // org.bukkit.craftbukkit.block.data.CraftWaterlogged

    private static final BooleanProperty WATERLOGGED = getBoolean(StainedGlassPaneBlock.class, "waterlogged");

    @Override
    public boolean isWaterlogged() {
        return get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(boolean waterlogged) {
        set(WATERLOGGED, waterlogged);
    }
}
