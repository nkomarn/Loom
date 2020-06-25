package org.bukkit.craftbukkit.boss;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.util.CraftChatMessage;
import org.bukkit.entity.Player;

public class CraftBossBar implements org.bukkit.boss.BossBar {

    private final ServerBossBar handle;
    private Map<BarFlag, FlagContainer> flags;

    public CraftBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
        handle = new ServerBossBar(
                CraftChatMessage.fromString(title, true)[0],
                convertColor(color),
                convertStyle(style)
        );

        this.initialize();

        for (BarFlag flag : flags) {
            this.addFlag(flag);
        }

        this.setColor(color);
        this.setStyle(style);
    }

    public CraftBossBar(ServerBossBar bossBattleServer) {
        this.handle = bossBattleServer;
        this.initialize();
    }

    private void initialize() {
        this.flags = new HashMap<>();
        this.flags.put(BarFlag.DARKEN_SKY, new FlagContainer(handle::getDarkenSky, handle::setDarkenSky));
        this.flags.put(BarFlag.PLAY_BOSS_MUSIC, new FlagContainer(handle::hasDragonMusic, handle::setDragonMusic));
        this.flags.put(BarFlag.CREATE_FOG, new FlagContainer(handle::getThickenFog, handle::setThickenFog));
    }

    private BarColor convertColor(BossBar.Color color) {
        BarColor bukkitColor = BarColor.valueOf(color.name());
        return (bukkitColor == null) ? BarColor.WHITE : bukkitColor;
    }

    private BossBar.Color convertColor(BarColor color) {
        BossBar.Color nmsColor = BossBar.Color.valueOf(color.name());
        return (nmsColor == null) ? BossBar.Color.WHITE : nmsColor;
    }

    private BossBar.Style convertStyle(BarStyle style) {
        switch (style) {
            default:
            case SOLID:
                return BossBar.Style.PROGRESS;
            case SEGMENTED_6:
                return BossBar.Style.NOTCHED_6;
            case SEGMENTED_10:
                return BossBar.Style.NOTCHED_10;
            case SEGMENTED_12:
                return BossBar.Style.NOTCHED_12;
            case SEGMENTED_20:
                return BossBar.Style.NOTCHED_20;
        }
    }

    private BarStyle convertStyle(BossBar.Style style) {
        switch (style) {
            default:
            case PROGRESS:
                return BarStyle.SOLID;
            case NOTCHED_6:
                return BarStyle.SEGMENTED_6;
            case NOTCHED_10:
                return BarStyle.SEGMENTED_10;
            case NOTCHED_12:
                return BarStyle.SEGMENTED_12;
            case NOTCHED_20:
                return BarStyle.SEGMENTED_20;
        }
    }

    @Override
    public String getTitle() {
        return CraftChatMessage.fromComponent(handle.getName());
    }

    @Override
    public void setTitle(String title) {
        handle.setName(CraftChatMessage.fromString(title, true)[0]);
    }

    @Override
    public BarColor getColor() {
        return convertColor(handle.getColor());
    }

    @Override
    public void setColor(BarColor color) {
        handle.setColor(convertColor(color));
    }

    @Override
    public BarStyle getStyle() {
        return convertStyle(handle.getOverlay());
    }

    @Override
    public void setStyle(BarStyle style) {
        handle.setOverlay(convertStyle(style);)
    }

    @Override
    public void addFlag(BarFlag flag) {
        FlagContainer flagContainer = flags.get(flag);
        if (flagContainer != null) {
            flagContainer.set.accept(true);
        }
    }

    @Override
    public void removeFlag(BarFlag flag) {
        FlagContainer flagContainer = flags.get(flag);
        if (flagContainer != null) {
            flagContainer.set.accept(false);
        }
    }

    @Override
    public boolean hasFlag(BarFlag flag) {
        FlagContainer flagContainer = flags.get(flag);
        if (flagContainer != null) {
            return flagContainer.get.get();
        }
        return false;
    }

    @Override
    public void setProgress(double progress) {
        Preconditions.checkArgument(progress >= 0.0 && progress <= 1.0,
                "Progress must be between 0.0 and 1.0 (%s)", progress);
        handle.setPercent((float) progress);
    }

    @Override
    public double getProgress() {
        return handle.getPercent();
    }

    @Override
    public void addPlayer(Player player) {
        Preconditions.checkArgument(player != null, "player == null");
        Preconditions.checkArgument(((CraftPlayer) player).getHandle().networkHandler != null,
                "player is not fully connected (wait for PlayerJoinEvent)");

        handle.addPlayer(((CraftPlayer) player).getHandle());
    }

    @Override
    public void removePlayer(Player player) {
        Preconditions.checkArgument(player != null, "player == null");

        handle.removePlayer(((CraftPlayer) player).getHandle());
    }

    @Override
    public List<Player> getPlayers() {
        ImmutableList.Builder<Player> players = ImmutableList.builder();
        for (ServerPlayerEntity p : handle.getPlayers()) {
            players.add(p.getBukkitEntity());
        }
        return players.build();
    }

    @Override
    public void setVisible(boolean visible) {
        handle.setVisible(visible);
    }

    @Override
    public boolean isVisible() {
        return handle.isVisible();
    }

    @Override
    public void show() {
        handle.setVisible(true);
    }

    @Override
    public void hide() {
        handle.setVisible(false);
    }

    @Override
    public void removeAll() {
        for (Player player : getPlayers()) {
            removePlayer(player);
        }
    }

    private final class FlagContainer {

        private Supplier<Boolean> get;
        private Consumer<Boolean> set;

        private FlagContainer(Supplier<Boolean> get, Consumer<Boolean> set) {
            this.get = get;
            this.set = set;
        }
    }

    public ServerBossBar getHandle() {
        return handle;
    }
}
