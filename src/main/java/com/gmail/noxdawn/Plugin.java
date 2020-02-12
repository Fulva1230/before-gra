package com.gmail.noxdawn;

import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.logging.Level;

public class Plugin extends JavaPlugin {
    private AnnotationConfigApplicationContext context;
    
    @Override
    public void onEnable() {
        super.onEnable();
        makeContextReady();
        final Map<String, Enabler> enablersMap = context.getBeansOfType(Enabler.class);
        getLogger().log((Level) context.getBean("debugLevel"), String.format("there are %d enablers needed to be enabled", enablersMap.size()));
        enablersMap.values().forEach(Enabler::enable);
        getLogger().info("enabled successfully");
    }
    
    private void makeContextReady() {
        context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.registerBean("plugin", JavaPlugin.class, () -> this);
        context.refresh();
    }
}
