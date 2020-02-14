package com.gmail.noxdawn;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.logging.Logger;

public class EventEnabler implements Enabler {
    private final Map<String, Listener> listeners;
    private final JavaPlugin plugin;
    private final Logger logger;
    
    public EventEnabler(Map<String, Listener> listeners, JavaPlugin plugin, Logger logger) {
        this.listeners = listeners;
        this.plugin = plugin;
        this.logger = logger;
    }
    
    @Override
    public void enable() {
        logger.log(logger.getLevel(), String.format("There are %d listeners need to be registered", listeners.size()));
        for (Map.Entry<String, Listener> listenerEntry : listeners.entrySet()) {
            logger.log(logger.getLevel(), String.format("Registering %s", listenerEntry.getKey()));
            plugin.getServer().getPluginManager().registerEvents(listenerEntry.getValue(), plugin);
        }
    }
}