package com.gmail.noxdawn.contiarrow;

import com.gmail.noxdawn.playerstate.PersistentDataController;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.logging.Logger;

@Configuration
public class ContiArrowConfig {
    @Bean
    @Qualifier("startup")
    public ContiArrowInteractListener contiArrowInteractListener(Logger logger, ContiArrowItemStackUtil contiArrowItemStackUtil, JavaPlugin plugin) {
        return new ContiArrowInteractListener(logger, contiArrowItemStackUtil) {
            @Override
            protected Consumer<Player> playerConsumer() {
                return new ArrowLaunch(plugin);
            }
        };
    }
    
    @Bean
    public PersistentDataController.Builder contiArrowPersistenDataControllerBuilder(JavaPlugin plugin) {
        return new PersistentDataController.Builder(plugin, "contiarrow");
    }
    
    @Bean
    public ContiArrowItemStackUtil contiArrowItemStackUtil(PersistentDataController.Builder contiArrowPersistenDataControllerBuilder) {
        return new ContiArrowItemStackUtil(contiArrowPersistenDataControllerBuilder);
    }
    
    @Bean("contiarrowitem")
    public ContiArrowSetCommand contiArrowSetCommand(ContiArrowItemStackUtil contiArrowItemStackUtil) {
        return new ContiArrowSetCommand(contiArrowItemStackUtil);
    }
}