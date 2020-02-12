package com.gmail.noxdawn;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.logging.Level;

public class CommandEnabler implements Enabler {
    private final JavaPlugin plugin;
    private final Map<String, PluginCommandExecutor> executors;
    private final Level loglevel;
    
    public CommandEnabler(JavaPlugin plugin, Map<String, PluginCommandExecutor> executors, Level loglevel) {
        this.plugin = plugin;
        this.executors = executors;
        this.loglevel = loglevel;
    }
    
    @Override
    public void enable() {
        plugin.getLogger().log(loglevel, String.format("There are %d commands need to be registered", executors.size()));
        detailedLogCommands();
        registerCommands();
    }
    
    private void detailedLogCommands() {
        for (Map.Entry<String, PluginCommandExecutor> entry : executors.entrySet()) {
            plugin.getLogger().log(loglevel, String.format("%s need to be registered", entry.getKey()));
        }
    }
    
    private void registerCommands() {
        for (Map.Entry<String, PluginCommandExecutor> entry : executors.entrySet()) {
            plugin.getLogger().log(loglevel, String.format("registering %s", entry.getKey()));
            plugin.getCommand(entry.getKey()).setExecutor(entry.getValue());
        }
    }
}
