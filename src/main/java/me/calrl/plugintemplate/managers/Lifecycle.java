package me.calrl.plugintemplate.managers;

/** Defines lifecycle methods for a plugin component. */
public interface Lifecycle {

  /** Called when the plugin is enabled. */
  void onEnable();

  /** Called when the plugin is reloaded. */
  void onReload();

  /** Called when the plugin is disabled. */
  void onDisable();
}
