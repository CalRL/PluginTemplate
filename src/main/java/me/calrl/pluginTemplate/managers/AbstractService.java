package me.calrl.pluginTemplate.managers;

import me.calrl.pluginTemplate.PluginTemplate;
import me.calrl.pluginTemplate.utils.DebugMode;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService implements ILifecycle {
    protected final PluginTemplate plugin;
    private final List<ILifecycle> children = new ArrayList<>();

    protected AbstractService(PluginTemplate plugin) {
        this.plugin = plugin;
    }

    protected <T extends ILifecycle> T register(T child) {
        children.add(child);
        return child;
    }

    @Override
    public void onEnable() {
        DebugMode debug = new DebugMode(plugin);
        for (ILifecycle conf : children) {
            conf.onEnable();
            debug.info(String.format("Enabling: %s", conf.getClass().descriptorString()));
        }
    }

    @Override
    public void onReload() {
        DebugMode debug = new DebugMode(plugin);
        for (ILifecycle conf : children) {
            conf.onReload();
            debug.info(String.format("Reloading: %s", conf.getClass().descriptorString()));
        }
    }

    @Override
    public void onDisable() {
        for (ILifecycle conf : children) {
            conf.onDisable();
        }
    }
}
