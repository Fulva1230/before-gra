package com.gmail.noxdawn;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.logging.Level;

public class Plugin extends JavaPlugin {
    private AnnotationConfigApplicationContext context;
    
    @Override
    public void onEnable() {
        super.onEnable();
        saveDefaultConfig();
        makeContextReady();
        final Map<String, Enabler> enablersMap = context.getBeansOfType(Enabler.class);
        getLogger().log((Level) context.getBean("debugLevel"), String.format("there are %d enablers needed to be enabled", enablersMap.size()));
        enablersMap.values().forEach(Enabler::enable);
        getLogger().info("enabled successfully");
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        final Map<String, Disabler> disablerMap = context.getBeansOfType(Disabler.class);
        disablerMap.values().forEach(Disabler::disable);
    }
    
    private void makeContextReady() {
        context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.registerBean("plugin", JavaPlugin.class, () -> this);
        context.registerBean("scheduler", BukkitScheduler.class, () -> this.getServer().getScheduler());
        context.registerBean("server", Server.class, this::getServer);
        context.registerBean("fileConfiguration", FileConfiguration.class, this::getConfig);
        context.refresh();
    }
}
