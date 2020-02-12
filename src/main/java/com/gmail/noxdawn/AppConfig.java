package com.gmail.noxdawn;

import com.gmail.noxdawn.funcommands.CommandsConfig;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Map;
import java.util.logging.Level;

@Configuration
@Import({CommandsConfig.class})
public class AppConfig {
    @Bean
    Level debugLevel() {
        return Level.INFO;
    }
    
    @Bean
    Level allLevel() {
        return Level.ALL;
    }
    
    @Bean
    Enabler commandEnabler(JavaPlugin plugin, Map<String, PluginCommandExecutor> executorMap, Level debugLevel) {
        return new CommandEnabler(plugin, executorMap, debugLevel);
    }
}
