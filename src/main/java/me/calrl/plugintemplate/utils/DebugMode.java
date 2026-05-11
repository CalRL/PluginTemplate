package me.calrl.plugintemplate.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.calrl.plugintemplate.PluginTemplate;
import org.bukkit.configuration.file.FileConfiguration;

/** Utility for debug-mode logging controlled by the plugin config. */
public class DebugMode {

  private final FileConfiguration config;
  private final Logger logger;

  /** Constructs a DebugMode.
   *
   * @param plugin the plugin instance
   */
  public DebugMode(PluginTemplate plugin) {
    this.config = plugin.getConfig();
    this.logger = plugin.getLogger();
  }

  /** Logs an info-level debug message.
   *
   * @param message the message
   */
  public void info(String message) {
    if (config.getBoolean("debug")) {
      logger.log(Level.INFO, "[DEBUG] " + message);
    }
  }

  /** Logs a warning-level debug message.
   *
   * @param message the message
   */
  public void warn(String message) {
    if (config.getBoolean("debug")) {
      logger.log(Level.WARNING, "[DEBUG] " + message);
    }
  }

  /** Logs a severe-level debug message.
   *
   * @param message the message
   */
  public void severe(String message) {
    if (config.getBoolean("debug")) {
      logger.log(Level.SEVERE, "[DEBUG] " + message);
    }
  }
}
