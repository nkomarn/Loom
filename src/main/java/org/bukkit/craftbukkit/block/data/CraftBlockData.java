package org.bukkit.craftbukkit.block.data;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.block.*;
import net.minecraft.command.arguments.BlockArgumentParser;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.State;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.craftbukkit.block.impl.CraftFarmland;
import org.bukkit.craftbukkit.block.impl.CraftFurnace;
import org.bukkit.craftbukkit.block.impl.CraftMycelium;
import org.bukkit.craftbukkit.block.impl.CraftSugarcane;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;

public class CraftBlockData implements BlockData {

    private BlockState state;
    private Map<Property<?>, Comparable<?>> parsedStates;

    protected CraftBlockData() {
        throw new AssertionError("Template Constructor");
    }

    protected CraftBlockData(BlockState state) {
        this.state = state;
    }

    @Override
    public Material getMaterial() {
        return CraftMagicNumbers.getMaterial(state.getBlock());
    }

    public BlockState getState() {
        return state;
    }

    /**
     * Get a given BlockStateEnum's value as its Bukkit counterpart.
     *
     * @param nms the NMS state to convert
     * @param bukkit the Bukkit class
     * @param <B> the type
     * @return the matching Bukkit type
     */
    protected <B extends Enum<B>> B get(EnumProperty<?> nms, Class<B> bukkit) {
        return toBukkit(state.get(nms), bukkit);
    }

    /**
     * Convert all values from the given BlockStateEnum to their appropriate
     * Bukkit counterpart.
     *
     * @param nms the NMS state to get values from
     * @param bukkit the bukkit class to convert the values to
     * @param <B> the bukkit class type
     * @return an immutable Set of values in their appropriate Bukkit type
     */
    @SuppressWarnings("unchecked")
    protected <B extends Enum<B>> Set<B> getValues(EnumProperty<?> nms, Class<B> bukkit) {
        ImmutableSet.Builder<B> values = ImmutableSet.builder();

        for (Enum<?> e : nms.getValues()) {
            values.add(toBukkit(e, bukkit));
        }

        return values.build();
    }

    /**
     * Set a given {@link EnumProperty} with the matching enum from Bukkit.
     *
     * @param nms the NMS BlockStateEnum to set
     * @param bukkit the matching Bukkit Enum
     * @param <B> the Bukkit type
     * @param <N> the NMS type
     */
    protected <B extends Enum<B>, N extends Enum<N> & StringIdentifiable> void set(EnumProperty<N> nms, Enum<B> bukkit) {
        this.parsedStates = null;
        this.state = this.state.with(nms, toNMS(bukkit, nms.getType()));
    }

    @Override
    public BlockData merge(BlockData data) {
        CraftBlockData craft = (CraftBlockData) data;
        Preconditions.checkArgument(craft.parsedStates != null, "Data not created via string parsing");
        Preconditions.checkArgument(this.state.getBlock() == craft.state.getBlock(), "States have different types (got %s, expected %s)", data, this);

        CraftBlockData clone = (CraftBlockData) this.clone();
        clone.parsedStates = null;

        for (Property parsed : craft.parsedStates.keySet()) { // TODO
            clone.state = clone.state.with(parsed, craft.state.get(parsed));
        }

        return clone;
    }

    @Override
    public boolean matches(BlockData data) {
        if (data == null) {
            return false;
        }
        if (!(data instanceof CraftBlockData)) {
            return false;
        }

        CraftBlockData craft = (CraftBlockData) data;
        if (this.state.getBlock() != craft.state.getBlock()) {
            return false;
        }

        // Fastpath an exact match
        boolean exactMatch = this.equals(data);

        // If that failed, do a merge and check
        if (!exactMatch && craft.parsedStates != null) {
            return this.merge(data).equals(this);
        }

        return exactMatch;
    }

    private static final Map<Class, BiMap<Enum<?>, Enum<?>>> classMappings = new HashMap<>();

    /**
     * Convert an NMS Enum (usually a BlockStateEnum) to its appropriate Bukkit
     * enum from the given class.
     *
     * @throws IllegalStateException if the Enum could not be converted
     */
    @SuppressWarnings("unchecked")
    private static <B extends Enum<B>> B toBukkit(Enum<?> nms, Class<B> bukkit) {
        Enum<?> converted;
        BiMap<Enum<?>, Enum<?>> nmsToBukkit = classMappings.get(nms.getClass());

        if (nmsToBukkit != null) {
            converted = nmsToBukkit.get(nms);
            if (converted != null) {
                return (B) converted;
            }
        }

        if (nms instanceof Direction) {
            converted = CraftBlock.notchToBlockFace((Direction) nms);
        } else {
            converted = bukkit.getEnumConstants()[nms.ordinal()];
        }

        Preconditions.checkState(converted != null, "Could not convert enum %s->%s", nms, bukkit);

        if (nmsToBukkit == null) {
            nmsToBukkit = HashBiMap.create();
            classMappings.put(nms.getClass(), nmsToBukkit);
        }

        nmsToBukkit.put(nms, converted);

        return (B) converted;
    }

    /**
     * Convert a given Bukkit enum to its matching NMS enum type.
     *
     * @param bukkit the Bukkit enum to convert
     * @param nms the NMS class
     * @return the matching NMS type
     * @throws IllegalStateException if the Enum could not be converted
     */
    @SuppressWarnings("unchecked")
    private static <N extends Enum<N> & StringIdentifiable> N toNMS(Enum<?> bukkit, Class<N> nms) {
        Enum<?> converted;
        BiMap<Enum<?>, Enum<?>> nmsToBukkit = classMappings.get(nms);

        if (nmsToBukkit != null) {
            converted = nmsToBukkit.inverse().get(bukkit);
            if (converted != null) {
                return (N) converted;
            }
        }

        if (bukkit instanceof BlockFace) {
            converted = CraftBlock.blockFaceToNotch((BlockFace) bukkit);
        } else {
            converted = nms.getEnumConstants()[bukkit.ordinal()];
        }

        Preconditions.checkState(converted != null, "Could not convert enum %s->%s", nms, bukkit);

        if (nmsToBukkit == null) {
            nmsToBukkit = HashBiMap.create();
            classMappings.put(nms, nmsToBukkit);
        }

        nmsToBukkit.put(converted, bukkit);

        return (N) converted;
    }

    /**
     * Get the current value of a given state.
     *
     * @param ibs the state to check
     * @param <T> the type
     * @return the current value of the given state
     */
    protected <T extends Comparable<T>> T get(Property<T> ibs) {
        // Straight integer or boolean getter
        return this.state.get(ibs);
    }

    /**
     * Set the specified state's value.
     *
     * @param ibs the state to set
     * @param v the new value
     * @param <T> the state's type
     * @param <V> the value's type. Must match the state's type.
     */
    public <T extends Comparable<T>, V extends T> void set(Property<T> ibs, V v) {
        // Straight integer or boolean setter
        this.parsedStates = null;
        this.state = this.state.with(ibs, v);
    }

    @Override
    public String getAsString() {
        return toString(state.getEntries());
    }

    @Override
    public String getAsString(boolean hideUnspecified) {
        return (hideUnspecified && parsedStates != null) ? toString(parsedStates) : getAsString();
    }

    @Override
    public BlockData clone() {
        try {
            return (BlockData) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError("Clone not supported", ex);
        }
    }

    @Override
    public String toString() {
        return "CraftBlockData{" + getAsString() + "}";
    }

    // Mimicked from BlockDataAbstract#toString()
    public String toString(Map<Property<?>, Comparable<?>> states) {
        StringBuilder stateString = new StringBuilder(Registry.BLOCK.getKey(state.getBlock()).toString());

        if (!states.isEmpty()) {
            stateString.append('[');
            stateString.append(states.entrySet().stream().map(State.STATE_TO_VALUE).collect(Collectors.joining(",")));
            stateString.append(']');
        }

        return stateString.toString();
    }

    public CompoundTag toStates() {
        CompoundTag compound = new CompoundTag();

        for (Map.Entry<Property<?>, Comparable<?>> entry : state.getEntries().entrySet()) {
            Property<?> iblockstate = entry.getKey();
            compound.putString(iblockstate.getName(), iblockstate.a(entry.getValue()));
        }

        return compound;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CraftBlockData && state.equals(((CraftBlockData) obj).state);
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    protected static BooleanProperty getBoolean(String name) {
        throw new AssertionError("Template Method");
    }

    protected static BooleanProperty getBoolean(String name, boolean optional) {
        throw new AssertionError("Template Method");
    }

    protected static EnumProperty<?> getEnum(String name) {
        throw new AssertionError("Template Method");
    }

    protected static IntProperty getInteger(String name) {
        throw new AssertionError("Template Method");
    }

    protected static BooleanProperty getBoolean(Class<? extends Block> block, String name) {
        return (BooleanProperty) getState(block, name, false);
    }

    protected static BooleanProperty getBoolean(Class<? extends Block> block, String name, boolean optional) {
        return (BooleanProperty) getState(block, name, optional);
    }

    protected static EnumProperty<?> getEnum(Class<? extends Block> block, String name) {
        return (EnumProperty<?>) getState(block, name, false);
    }

    protected static IntProperty getInteger(Class<? extends Block> block, String name) {
        return (IntProperty) getState(block, name, false);
    }

    /**
     * Get a specified {@link Property} from a given block's class with a
     * given name
     *
     * @param block the class to retrieve the state from
     * @param name the name of the state to retrieve
     * @param optional if the state can be null
     * @return the specified state or null
     * @throws IllegalStateException if the state is null and {@code optional}
     * is false.
     */
    private static Property<?> getState(Class<? extends Block> block, String name, boolean optional) {
        Property<?> state = null;

        for (Block instance : Registry.BLOCK) {
            if (instance.getClass() == block) {
                if (state == null) {
                    state = instance.getStates().a(name);
                } else {
                    Property<?> newState = instance.getStates().a(name);

                    Preconditions.checkState(state == newState, "State mistmatch %s,%s", state, newState);
                }
            }
        }

        Preconditions.checkState(optional || state != null, "Null state for %s,%s", block, name);

        return state;
    }

    /**
     * Get the minimum value allowed by the BlockStateInteger.
     *
     * @param state the state to check
     * @return the minimum value allowed
     */
    protected static int getMin(IntProperty state) {
        return state.min;
    }

    /**
     * Get the maximum value allowed by the BlockStateInteger.
     *
     * @param state the state to check
     * @return the maximum value allowed
     */
    protected static int getMax(IntProperty state) {
        return state.max;
    }

    //
    private static final Map<Class<? extends Block>, Function<BlockState, CraftBlockData>> MAP = new HashMap<>();

    static {
        //<editor-fold desc="CraftBlockData Registration" defaultstate="collapsed">
        register(AnvilBlock.class, org.bukkit.craftbukkit.block.impl.CraftAnvil::new);
        register(BambooBlock.class, org.bukkit.craftbukkit.block.impl.CraftBamboo::new);
        register(BannerBlock.class, org.bukkit.craftbukkit.block.impl.CraftBanner::new);
        register(WallBannerBlock.class, org.bukkit.craftbukkit.block.impl.CraftBannerWall::new);
        register(BarrelBlock.class, org.bukkit.craftbukkit.block.impl.CraftBarrel::new);
        register(BedBlock.class, org.bukkit.craftbukkit.block.impl.CraftBed::new);
        register(BeehiveBlock.class, org.bukkit.craftbukkit.block.impl.CraftBeehive::new);
        register(BeetrootsBlock.class, org.bukkit.craftbukkit.block.impl.CraftBeetroot::new);
        register(BellBlock.class, org.bukkit.craftbukkit.block.impl.CraftBell::new);
        register(BlastFurnaceBlock.class, org.bukkit.craftbukkit.block.impl.CraftBlastFurnace::new);
        register(BrewingStandBlock.class, org.bukkit.craftbukkit.block.impl.CraftBrewingStand::new);
        register(BubbleColumnBlock.class, org.bukkit.craftbukkit.block.impl.CraftBubbleColumn::new);
        register(CactusBlock.class, org.bukkit.craftbukkit.block.impl.CraftCactus::new);
        register(CakeBlock.class, org.bukkit.craftbukkit.block.impl.CraftCake::new);
        register(CampfireBlock.class, org.bukkit.craftbukkit.block.impl.CraftCampfire::new);
        register(CarrotsBlock.class, org.bukkit.craftbukkit.block.impl.CraftCarrots::new);
        register(CauldronBlock.class, org.bukkit.craftbukkit.block.impl.CraftCauldron::new);
        register(ChainBlock.class, org.bukkit.craftbukkit.block.impl.CraftChain::new);
        register(ChestBlock.class, org.bukkit.craftbukkit.block.impl.CraftChest::new);
        register(TrappedChestBlock.class, org.bukkit.craftbukkit.block.impl.CraftChestTrapped::new);
        register(ChorusFlowerBlock.class, org.bukkit.craftbukkit.block.impl.CraftChorusFlower::new);
        register(ChorusPlantBlock.class, org.bukkit.craftbukkit.block.impl.CraftChorusFruit::new);
        register(WallBlock.class, org.bukkit.craftbukkit.block.impl.CraftCobbleWall::new);
        register(CocoaBlock.class, org.bukkit.craftbukkit.block.impl.CraftCocoa::new);
        register(CommandBlock.class, org.bukkit.craftbukkit.block.impl.CraftCommand::new);
        register(ComposterBlock.class, org.bukkit.craftbukkit.block.impl.CraftComposter::new);
        register(ConduitBlock.class, org.bukkit.craftbukkit.block.impl.CraftConduit::new);
        register(DeadCoralBlock.class, org.bukkit.craftbukkit.block.impl.CraftCoralDead::new);
        register(CoralFanBlock.class, org.bukkit.craftbukkit.block.impl.CraftCoralFan::new);
        register(DeadCoralFanBlock.class, org.bukkit.craftbukkit.block.impl.CraftCoralFanAbstract::new);
        register(CoralWallFanBlock.class, org.bukkit.craftbukkit.block.impl.CraftCoralFanWall::new);
        register(DeadCoralWallFanBlock.class, org.bukkit.craftbukkit.block.impl.CraftCoralFanWallAbstract::new);
        register(CoralBlock.class, org.bukkit.craftbukkit.block.impl.CraftCoralPlant::new);
        register(CropBlock.class, org.bukkit.craftbukkit.block.impl.CraftCrops::new);
        register(DaylightDetectorBlock.class, org.bukkit.craftbukkit.block.impl.CraftDaylightDetector::new);
        register(SnowyBlock.class, org.bukkit.craftbukkit.block.impl.CraftDirtSnow::new);
        register(DispenserBlock.class, org.bukkit.craftbukkit.block.impl.CraftDispenser::new);
        register(DoorBlock.class, org.bukkit.craftbukkit.block.impl.CraftDoor::new);
        register(DropperBlock.class, org.bukkit.craftbukkit.block.impl.CraftDropper::new);
        register(EndRodBlock.class, org.bukkit.craftbukkit.block.impl.CraftEndRod::new);
        register(EnderChestBlock.class, org.bukkit.craftbukkit.block.impl.CraftEnderChest::new);
        register(EndPortalFrameBlock.class, org.bukkit.craftbukkit.block.impl.CraftEnderPortalFrame::new);
        register(FenceBlock.class, org.bukkit.craftbukkit.block.impl.CraftFence::new);
        register(FenceGateBlock.class, org.bukkit.craftbukkit.block.impl.CraftFenceGate::new);
        register(FireBlock.class, org.bukkit.craftbukkit.block.impl.CraftFire::new);
        register(SignBlock.class, org.bukkit.craftbukkit.block.impl.CraftFloorSign::new);
        register(FluidBlock.class, org.bukkit.craftbukkit.block.impl.CraftFluids::new);
        register(FurnaceBlock.class, CraftFurnace::new);
        register(GlazedTerracottaBlock.class, org.bukkit.craftbukkit.block.impl.CraftGlazedTerracotta::new);
        register(GrassBlock.class, org.bukkit.craftbukkit.block.impl.CraftGrass::new);
        register(GrindstoneBlock.class, org.bukkit.craftbukkit.block.impl.CraftGrindstone::new);
        register(HayBlock.class, org.bukkit.craftbukkit.block.impl.CraftHay::new);
        register(HopperBlock.class, org.bukkit.craftbukkit.block.impl.CraftHopper::new);
        register(MushroomBlock.class, org.bukkit.craftbukkit.block.impl.CraftHugeMushroom::new);
        register(FrostedIceBlock.class, org.bukkit.craftbukkit.block.impl.CraftIceFrost::new);
        register(PaneBlock.class, org.bukkit.craftbukkit.block.impl.CraftIronBars::new);
        register(JigsawBlock.class, org.bukkit.craftbukkit.block.impl.CraftJigsaw::new);
        register(JukeboxBlock.class, org.bukkit.craftbukkit.block.impl.CraftJukeBox::new);
        register(KelpBlock.class, org.bukkit.craftbukkit.block.impl.CraftKelp::new);
        register(LadderBlock.class, org.bukkit.craftbukkit.block.impl.CraftLadder::new);
        register(LanternBlock.class, org.bukkit.craftbukkit.block.impl.CraftLantern::new);
        register(LeavesBlock.class, org.bukkit.craftbukkit.block.impl.CraftLeaves::new);
        register(LecternBlock.class, org.bukkit.craftbukkit.block.impl.CraftLectern::new);
        register(LeverBlock.class, org.bukkit.craftbukkit.block.impl.CraftLever::new);
        register(LoomBlock.class, org.bukkit.craftbukkit.block.impl.CraftLoom::new);
        register(DetectorRailBlock.class, org.bukkit.craftbukkit.block.impl.CraftMinecartDetector::new);
        register(RailBlock.class, org.bukkit.craftbukkit.block.impl.CraftMinecartTrack::new);
        register(MyceliumBlock.class, CraftMycelium::new);
        register(NetherWartBlock.class, org.bukkit.craftbukkit.block.impl.CraftNetherWart::new);
        register(NoteBlock.class, org.bukkit.craftbukkit.block.impl.CraftNote::new);
        register(ObserverBlock.class, org.bukkit.craftbukkit.block.impl.CraftObserver::new);
        register(PistonBlock.class, org.bukkit.craftbukkit.block.impl.CraftPiston::new);
        register(PistonHeadBlock.class, org.bukkit.craftbukkit.block.impl.CraftPistonExtension::new);
        register(PistonExtensionBlock.class, org.bukkit.craftbukkit.block.impl.CraftPistonMoving::new);
        register(NetherPortalBlock.class, org.bukkit.craftbukkit.block.impl.CraftPortal::new);
        register(PotatoesBlock.class, org.bukkit.craftbukkit.block.impl.CraftPotatoes::new);
        register(PoweredRailBlock.class, org.bukkit.craftbukkit.block.impl.CraftPoweredRail::new);
        register(PressurePlateBlock.class, org.bukkit.craftbukkit.block.impl.CraftPressurePlateBinary::new);
        register(WeightedPressurePlateBlock.class, org.bukkit.craftbukkit.block.impl.CraftPressurePlateWeighted::new);
        register(CarvedPumpkinBlock.class, org.bukkit.craftbukkit.block.impl.CraftPumpkinCarved::new);
        register(ComparatorBlock.class, org.bukkit.craftbukkit.block.impl.CraftRedstoneComparator::new);
        register(RedstoneLampBlock.class, org.bukkit.craftbukkit.block.impl.CraftRedstoneLamp::new);
        register(RedstoneOreBlock.class, org.bukkit.craftbukkit.block.impl.CraftRedstoneOre::new);
        register(RedstoneTorchBlock.class, org.bukkit.craftbukkit.block.impl.CraftRedstoneTorch::new);
        register(WallRedstoneTorchBlock.class, org.bukkit.craftbukkit.block.impl.CraftRedstoneTorchWall::new);
        register(RedstoneWireBlock.class, org.bukkit.craftbukkit.block.impl.CraftRedstoneWire::new);
        register(SugarCaneBlock.class, CraftSugarcane::new);
        register(RepeaterBlock.class, org.bukkit.craftbukkit.block.impl.CraftRepeater::new);
        register(RespawnAnchorBlock.class, org.bukkit.craftbukkit.block.impl.CraftRespawnAnchor::new);
        register(PillarBlock.class, org.bukkit.craftbukkit.block.impl.CraftRotatable::new);
        register(SaplingBlock.class, org.bukkit.craftbukkit.block.impl.CraftSapling::new);
        register(ScaffoldingBlock.class, org.bukkit.craftbukkit.block.impl.CraftScaffolding::new);
        register(SeaPickleBlock.class, org.bukkit.craftbukkit.block.impl.CraftSeaPickle::new);
        register(ShulkerBoxBlock.class, org.bukkit.craftbukkit.block.impl.CraftShulkerBox::new);
        register(SkullBlock.class, org.bukkit.craftbukkit.block.impl.CraftSkull::new);
        register(PlayerSkullBlock.class, org.bukkit.craftbukkit.block.impl.CraftSkullPlayer::new);
        register(WallPlayerSkullBlock.class, org.bukkit.craftbukkit.block.impl.CraftSkullPlayerWall::new);
        register(WallSkullBlock.class, org.bukkit.craftbukkit.block.impl.CraftSkullWall::new);
        register(SmokerBlock.class, org.bukkit.craftbukkit.block.impl.CraftSmoker::new);
        register(SnowBlock.class, org.bukkit.craftbukkit.block.impl.CraftSnow::new);
        register(FarmlandBlock.class, CraftFarmland::new);
        register(StainedGlassPaneBlock.class, org.bukkit.craftbukkit.block.impl.CraftStainedGlassPane::new);
        register(StairsBlock.class, org.bukkit.craftbukkit.block.impl.CraftStairs::new);
        register(StemBlock.class, org.bukkit.craftbukkit.block.impl.CraftStem::new);
        register(AttachedStemBlock.class, org.bukkit.craftbukkit.block.impl.CraftStemAttached::new);
        register(SlabBlock.class, org.bukkit.craftbukkit.block.impl.CraftStepAbstract::new);
        register(StoneButtonBlock.class, org.bukkit.craftbukkit.block.impl.CraftStoneButton::new);
        register(StonecutterBlock.class, org.bukkit.craftbukkit.block.impl.CraftStonecutter::new);
        register(StructureBlock.class, org.bukkit.craftbukkit.block.impl.CraftStructure::new);
        register(SweetBerryBushBlock.class, org.bukkit.craftbukkit.block.impl.CraftSweetBerryBush::new);
        register(TallPlantBlock.class, org.bukkit.craftbukkit.block.impl.CraftTallPlant::new);
        register(TallFlowerBlock.class, org.bukkit.craftbukkit.block.impl.CraftTallPlantFlower::new);
        register(TallSeagrassBlock.class, org.bukkit.craftbukkit.block.impl.CraftTallSeaGrass::new);
        register(TargetBlock.class, org.bukkit.craftbukkit.block.impl.CraftTarget::new);
        register(TntBlock.class, org.bukkit.craftbukkit.block.impl.CraftTNT::new);
        register(WallTorchBlock.class, org.bukkit.craftbukkit.block.impl.CraftTorchWall::new);
        register(TrapdoorBlock.class, org.bukkit.craftbukkit.block.impl.CraftTrapdoor::new);
        register(TripwireBlock.class, org.bukkit.craftbukkit.block.impl.CraftTripwire::new);
        register(TripwireHookBlock.class, org.bukkit.craftbukkit.block.impl.CraftTripwireHook::new);
        register(TurtleEggBlock.class, org.bukkit.craftbukkit.block.impl.CraftTurtleEgg::new);
        register(TwistingVinesBlock.class, org.bukkit.craftbukkit.block.impl.CraftTwistingVines::new);
        register(VineBlock.class, org.bukkit.craftbukkit.block.impl.CraftVine::new);
        register(WallSignBlock.class, org.bukkit.craftbukkit.block.impl.CraftWallSign::new);
        register(WeepingVinesBlock.class, org.bukkit.craftbukkit.block.impl.CraftWeepingVines::new);
        register(WitherSkullBlock.class, org.bukkit.craftbukkit.block.impl.CraftWitherSkull::new);
        register(WallWitherSkullBlock.class, org.bukkit.craftbukkit.block.impl.CraftWitherSkullWall::new);
        register(WoodButtonBlock.class, org.bukkit.craftbukkit.block.impl.CraftWoodButton::new);
        //</editor-fold>
    }

    private static void register(Class<? extends Block> nms, Function<BlockState, CraftBlockData> bukkit) {
        Preconditions.checkState(MAP.put(nms, bukkit) == null, "Duplicate mapping %s->%s", nms, bukkit);
    }

    public static CraftBlockData newData(Material material, String data) {
        Preconditions.checkArgument(material == null || material.isBlock(), "Cannot get data for not block %s", material);

        BlockState blockData;
        Block block = CraftMagicNumbers.getBlock(material);
        Map<Property<?>, Comparable<?>> parsed = null;

        // Data provided, use it
        if (data != null) {
            try {
                // Material provided, force that material in
                if (block != null) {
                    data = Registry.BLOCK.getKey(block) + data;
                }

                StringReader reader = new StringReader(data);
                BlockArgumentParser arg = new BlockArgumentParser(reader, false).a(false);
                Preconditions.checkArgument(!reader.canRead(), "Spurious trailing data: " + data);

                blockData = arg.getBlockState();
                parsed = arg.getBlockProperties();
            } catch (CommandSyntaxException ex) {
                throw new IllegalArgumentException("Could not parse data: " + data, ex);
            }
        } else {
            blockData = block.getDefaultState();
        }

        CraftBlockData craft = fromData(blockData);
        craft.parsedStates = parsed;
        return craft;
    }

    public static CraftBlockData fromData(BlockState data) {
        return MAP.getOrDefault(data.getBlock().getClass(), CraftBlockData::new).apply(data);
    }
}
