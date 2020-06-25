/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.TripwireBlock;
import net.minecraft.state.property.BooleanProperty;

public final class CraftTripwire extends org.bukkit.craftbukkit.block.data.CraftBlockData implements org.bukkit.block.data.type.Tripwire, org.bukkit.block.data.Attachable, org.bukkit.block.data.MultipleFacing, org.bukkit.block.data.Powerable {

    public CraftTripwire() {
        super();
    }

    public CraftTripwire(BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.block.data.type.CraftTripwire

    private static final BooleanProperty DISARMED = getBoolean(TripwireBlock.class, "disarmed");

    @Override
    public boolean isDisarmed() {
        return get(DISARMED);
    }

    @Override
    public void setDisarmed(boolean disarmed) {
        set(DISARMED, disarmed);
    }

    // org.bukkit.craftbukkit.block.data.CraftAttachable

    private static final BooleanProperty ATTACHED = getBoolean(TripwireBlock.class, "attached");

    @Override
    public boolean isAttached() {
        return get(ATTACHED);
    }

    @Override
    public void setAttached(boolean attached) {
        set(ATTACHED, attached);
    }

    // org.bukkit.craftbukkit.block.data.CraftMultipleFacing

    private static final BooleanProperty[] FACES = new BooleanProperty[]{
            getBoolean(TripwireBlock.class, "north", true), getBoolean(TripwireBlock.class, "east", true),
            getBoolean(TripwireBlock.class, "south", true), getBoolean(TripwireBlock.class, "west", true),
            getBoolean(TripwireBlock.class, "up", true), getBoolean(TripwireBlock.class, "down", true)
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

    // org.bukkit.craftbukkit.block.data.CraftPowerable

    private static final BooleanProperty POWERED = getBoolean(TripwireBlock.class, "powered");

    @Override
    public boolean isPowered() {
        return get(POWERED);
    }

    @Override
    public void setPowered(boolean powered) {
        set(POWERED, powered);
    }
}
