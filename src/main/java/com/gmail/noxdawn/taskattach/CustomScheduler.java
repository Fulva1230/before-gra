package com.gmail.noxdawn.taskattach;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class CustomScheduler {
    private final BukkitScheduler scheduler;
    private final JavaPlugin plugin;
    
    public CustomScheduler(BukkitScheduler scheduler, JavaPlugin plugin) {
        this.scheduler = scheduler;
        this.plugin = plugin;
    }
    
    public BukkitTask runTask(SelfStopTask selfStopTask) {
        return scheduler.runTaskTimer(plugin, selfStopTask, 2, 2);
    }
    
}
