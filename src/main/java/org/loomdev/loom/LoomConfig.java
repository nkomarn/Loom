package org.loomdev.loom;

import net.minecraft.server.MinecraftServer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.loomdev.loom.command.TpsCommand;

import java.io.File;

public class LoomConfig {
    private static YamlConfiguration config;

    public LoomConfig(File configFile) {
        /*config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Failed to load Loom configuration.", e);
            throw new RuntimeException(e);
        }

        config.options()
                .header("Loom Configuration File")
                .copyDefaults(true);*/

        // TODO version checking shit

        // Register commands
        MinecraftServer.getServer().server.getCommandMap().register("loom", new TpsCommand());
    }

}
