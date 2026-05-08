package me.calrl.pluginTemplate.utils;

import me.calrl.pluginTemplate.PluginTemplate;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DebugMode {
    private final FileConfiguration config;
    private final Logger logger;

    public DebugMode(PluginTemplate plugin) {
        this.config = plugin.getConfig();
        this.logger = plugin.getLogger();
    }

    public void info(String message) {
        if (config.getBoolean("debug")) {
            logger.log(Level.INFO, "[DEBUG] " + message);
        }
    }

    public void warn(String message) {
        if (config.getBoolean("debug")) {
            logger.log(Level.WARNING, "[DEBUG] " + message);
        }
    }

    public void severe(String message) {
        if (config.getBoolean("debug")) {
            logger.log(Level.SEVERE, "[DEBUG] " + message);
        }
    }
}
