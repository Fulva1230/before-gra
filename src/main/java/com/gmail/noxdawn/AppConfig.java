package com.gmail.noxdawn;

import com.gmail.noxdawn.funcommands.CommandsConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@Import({
        CommandsConfig.class,
        com.gmail.noxdawn.superdrop.SuperDropConfig.class,
        com.gmail.noxdawn.nofallarrow.NoFallArrowConfig.class,
        com.gmail.noxdawn.creeper.attacher.CreeperAttacherConfig.class,
        com.gmail.noxdawn.creeper.sound.CreeperSoundConfig.class,
        com.gmail.noxdawn.contiarrow.ContiArrowConfig.class
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
    
    @Bean
    public Properties properties(FileConfiguration fileConfiguration) {
        PropertyConverter proertiyConverter = new PropertyConverter(fileConfiguration);
        return proertiyConverter.get();
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(Properties properties) {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setProperties(properties);
        return propertySourcesPlaceholderConfigurer;
    }
    
    @Bean
    public Enabler debugEnabler(Logger debugLogger, Properties properties) {
        return () -> {
            Set<String> keys = properties.stringPropertyNames();
            for (String key : keys) {
                debugLogger.log(debugLogger.getLevel(), String.format("%s has value %s", key, properties.getProperty(key)));
            }
        };
    }
}
