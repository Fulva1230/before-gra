package com.gmail.noxdawn;

import com.gmail.noxdawn.funcommands.CommandsConfig;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@EnableAspectJAutoProxy
@Import({
        CommandsConfig.class,
        com.gmail.noxdawn.superdrop.SuperDropConfig.class,
        com.gmail.noxdawn.nofallarrow.NoFallArrowConfig.class,
        com.gmail.noxdawn.creeper.attacher.CreeperAttacherConfig.class
})
public class AppConfig {
    @Bean
    public Logger debugLogger(JavaPlugin plugin) {
        final Logger logger = plugin.getLogger();
        logger.setLevel(Level.INFO);
        return logger;
    }
    
    @Bean
    public Level debugLevel() {
        return Level.INFO;
    }
    
    public SimpleLogger simpleLogger(Logger debugLogger) {
        return new SimpleLogger(debugLogger);
    }
    
    @Bean
    public Level allLevel() {
        return Level.ALL;
    }
    
    @Bean
    public Enabler commandEnabler(JavaPlugin plugin, Map<String, PluginCommandExecutor> executorMap, Level debugLevel) {
        return new CommandEnabler(plugin, executorMap, debugLevel);
    }
    
    @Bean
    public Enabler eventEnabler(JavaPlugin plugin, @Qualifier("startup") Map<String, Listener> listenerMap, Logger debugLogger) {
        return new EventEnabler(listenerMap, plugin, debugLogger);
    }
}
