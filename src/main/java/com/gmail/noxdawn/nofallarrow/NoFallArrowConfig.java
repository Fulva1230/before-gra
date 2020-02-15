package com.gmail.noxdawn.nofallarrow;

import com.gmail.noxdawn.PluginCommandExecutor;
import com.gmail.noxdawn.playerstate.PersistentDataController;
import com.gmail.noxdawn.playerstate.PlayerStateCommandExecutor;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class NoFallArrowConfig {
    @Bean
    @Qualifier("startup")
    public Listener launchArrowListener(PersistentDataController.Builder noFallArrowPlayerStateControllerBuilder, Consumer<AbstractArrow> arrowModifier) {
        return new LaunchArrowListener(noFallArrowPlayerStateControllerBuilder, arrowModifier);
    }
    
    @Bean
    public Consumer<AbstractArrow> arrowModifier() {
        return new NoFallArrowConsumer();
    }
    
    @Bean("setnofallarrow")
    public PluginCommandExecutor noFallArrowSetCommandExecutor(PersistentDataController.Builder noFallArrowPlayerStateControllerBuilder) {
        return new PlayerStateCommandExecutor(noFallArrowPlayerStateControllerBuilder, noFallArrowSetCommandStrategy());
    }
    
    @Bean("removenofallarrow")
    public PluginCommandExecutor noFallArrowRemoveCommandExecutor(PersistentDataController.Builder noFallArrowPlayerStateControllerBuilder) {
        return new PlayerStateCommandExecutor(noFallArrowPlayerStateControllerBuilder, noFallArrowRemoveCommandStrategy());
    }
    
    @Bean("isnofallarrow")
    public PluginCommandExecutor noFallArrowQueryCommandExecutor(PersistentDataController.Builder noFallArrowPlayerStateControllerBuilder) {
        return new PlayerStateCommandExecutor(noFallArrowPlayerStateControllerBuilder, noFallArrowQueryCommandStrategy());
    }
    
    @Bean
    public PersistentDataController.Builder noFallArrowPlayerStateControllerBuilder(JavaPlugin plugin) {
        return new PersistentDataController.Builder(plugin, "nofallarrow");
    }
    
    @Bean
    public NoFallArrowSetCommandStrategy noFallArrowSetCommandStrategy() {
        return new NoFallArrowSetCommandStrategy();
    }
    
    @Bean
    public NoFallArrowQueryCommandStrategy noFallArrowQueryCommandStrategy() {
        return new NoFallArrowQueryCommandStrategy();
    }
    
    @Bean
    public NoFallArrowRemoveCommandStrategy noFallArrowRemoveCommandStrategy() {
        return new NoFallArrowRemoveCommandStrategy();
    }
    
    
}
