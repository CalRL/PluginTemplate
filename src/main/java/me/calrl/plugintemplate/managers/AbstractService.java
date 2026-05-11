package me.calrl.plugintemplate.managers;

import java.util.ArrayList;
import java.util.List;
import me.calrl.plugintemplate.PluginTemplate;
import me.calrl.plugintemplate.utils.DebugMode;

/** Abstract base class for services that manage child lifecycle components. */
public abstract class AbstractService implements Lifecycle {

  protected final PluginTemplate plugin;
  private final List<Lifecycle> children = new ArrayList<>();

  /** Constructs an AbstractService.
   *
   * @param plugin the plugin instance
   */
  protected AbstractService(PluginTemplate plugin) {
    this.plugin = plugin;
  }

  /** Registers a child lifecycle component.
   *
   * @param <T> the type of the child
   * @param child the child to register
   * @return the registered child
   */
  protected <T extends Lifecycle> T register(T child) {
    children.add(child);
    return child;
  }

  @Override
  public void onEnable() {
    DebugMode debug = new DebugMode(plugin);
    for (Lifecycle conf : children) {
      conf.onEnable();
      debug.info(String.format("Enabling: %s", conf.getClass().descriptorString()));
    }
  }

  @Override
  public void onReload() {
    DebugMode debug = new DebugMode(plugin);
    for (Lifecycle conf : children) {
      conf.onReload();
      debug.info(String.format("Reloading: %s", conf.getClass().descriptorString()));
    }
  }

  @Override
  public void onDisable() {
    for (Lifecycle conf : children) {
      conf.onDisable();
    }
  }
}
