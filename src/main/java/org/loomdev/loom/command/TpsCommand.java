package org.loomdev.loom.command;

import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A command to view the TPS and tick rates of the server.
 * Borrowed from Spigot and Paper.
 */
public class TpsCommand extends Command {
    private static final DecimalFormat FORMAT = new DecimalFormat("########0.0");

    public TpsCommand() {
        super("tps");
        this.description = "View server TPS and tick rates.";
        this.usageMessage = "/tps";
        this.setPermission("loom.command.tps");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!testPermission(sender)) {
            return true;
        }

        MinecraftServer server = MinecraftServer.getServer();

        // double[] tps = org.bukkit.Bukkit.getTPS(); // TODO add to api
        double[] tps = MinecraftServer.getServer().server.getTps();
        String[] tpsAvg = new String[tps.length];

        for ( int i = 0; i < tps.length; i++) {
            tpsAvg[i] = formatTps(tps[i]);
        }

        List<String> times = new ArrayList<>();
        times.addAll(eval(server.tickTimes5s.getTimes()));
        times.addAll(eval(server.tickTimes10s.getTimes()));
        times.addAll(eval(server.tickTimes60s.getTimes()));

        sender.sendMessage(String.format("§6TPS from last 1m, 5m, 15m: " + StringUtils.join(tpsAvg, ", ")));
        sender.sendMessage(String.format("§6Tick rates from last 5s, 10s, 15s:"));
        sender.sendMessage(String.format("%s§7/%s§7/%s§e, %s§7/%s§7/%s§e, %s§7/%s§7/%s", times.toArray()));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return Collections.emptyList();
    }

    private static String formatTps(double tps) {
        return ((tps > 18.0) ? ChatColor.GREEN : (tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED).toString()
                + ((tps > 21.0) ? "*" : "") + Math.min(Math.round(tps * 100.0) / 100.0, 20.0);
    }

    private static List<String> eval(long[] times) {
        long min = Integer.MAX_VALUE;
        long max = 0L;
        long total = 0L;
        for (long value : times) {
            if (value > 0L && value < min) min = value;
            if (value > max) max = value;
            total += value;
        }
        double avgD = ((double) total / (double) times.length) * 1.0E-6D;
        double minD = ((double) min) * 1.0E-6D;
        double maxD = ((double) max) * 1.0E-6D;
        return Arrays.asList(getColor(avgD), getColor(minD), getColor(maxD));
    }

    private static String getColor(double avg) {
        return ChatColor.COLOR_CHAR + (avg >= 50 ? "c" : avg >= 40 ? "e" : "a") + FORMAT.format(avg);
    }
}
