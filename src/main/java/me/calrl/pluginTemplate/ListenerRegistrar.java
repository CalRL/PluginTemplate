package me.calrl.pluginTemplate;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import java.util.logging.Logger;

public class ListenerRegistrar {
    private PluginTemplate plugin;
    public ListenerRegistrar(PluginTemplate plugin) {

    }
    private void registerListener(Listener listener, String enabledPath) {
        FileConfiguration config = this.plugin.getConfig();

        if(config.getBoolean(enabledPath) || enabledPath.equals("null")) {
            try {
                Server server = plugin.getServer();
                server.getPluginManager().registerEvents(listener, plugin);
            } catch(Exception e) {
                Logger logger = plugin.getLogger();
                logger.severe("PLEASE REPORT TO DEVELOPER");
                logger.severe(listener.getClass().getName()  + " failed to load, printing stacktrace...");
                logger.severe(e.getMessage());
            }
        }
    }

    private void registerListener(Listener listener) {
        registerListener(listener, "null");
    }
}
