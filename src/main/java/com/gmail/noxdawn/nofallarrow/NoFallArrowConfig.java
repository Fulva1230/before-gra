package com.gmail.noxdawn.nofallarrow;

import com.gmail.noxdawn.PluginCommandExecutor;
import com.gmail.noxdawn.playerstate.PlayerStateCommandExecutor;
import com.gmail.noxdawn.playerstate.PlayerStateController;
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
    public Listener launchArrowListener(PlayerStateController.Builder noFallArrowPlayerStateControllerBuilder, Consumer<AbstractArrow> arrowModifier) {
        return new LaunchArrowListener(noFallArrowPlayerStateControllerBuilder, arrowModifier);
    }
    
    @Bean
    public Consumer<AbstractArrow> arrowModifier() {
        return new NoFallArrowConsumer();
    }
    
    @Bean("setnofallarrow")
    public PluginCommandExecutor noFallArrowSetCommandExecutor(PlayerStateController.Builder noFallArrowPlayerStateControllerBuilder) {
        return new PlayerStateCommandExecutor(noFallArrowPlayerStateControllerBuilder, noFallArrowSetCommandStrategy());
    }
    
    @Bean("removenofallarrow")
    public PluginCommandExecutor noFallArrowRemoveCommandExecutor(PlayerStateController.Builder noFallArrowPlayerStateControllerBuilder) {
        return new PlayerStateCommandExecutor(noFallArrowPlayerStateControllerBuilder, noFallArrowRemoveCommandStrategy());
    }
    
    @Bean("isnofallarrow")
    public PluginCommandExecutor noFallArrowQueryCommandExecutor(PlayerStateController.Builder noFallArrowPlayerStateControllerBuilder) {
        return new PlayerStateCommandExecutor(noFallArrowPlayerStateControllerBuilder, noFallArrowQueryCommandStrategy());
    }
    
    @Bean
    public PlayerStateController.Builder noFallArrowPlayerStateControllerBuilder(JavaPlugin plugin) {
        return new PlayerStateController.Builder(plugin, "nofallarrow");
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
