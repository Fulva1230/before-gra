package com.gmail.noxdawn.superdrop;

import com.gmail.noxdawn.PluginCommandExecutor;
import com.gmail.noxdawn.playerstate.PersistentDataController;
import com.gmail.noxdawn.playerstate.PlayerStateCommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SuperDropConfig {
    @Bean
    @Qualifier("startup")
    public Listener dropListener(PersistentDataController.Builder superDropPlayerStateControllerBuilder, ItemSpeeder itemSpeeder) {
        return new DropListener(superDropPlayerStateControllerBuilder, itemSpeeder);
    }
    
    @Bean("setsuperdrop")
    public PluginCommandExecutor superDropSetCommandExecutor(PersistentDataController.Builder superDropPlayerStateControllerBuilder) {
        return new PlayerStateCommandExecutor(superDropPlayerStateControllerBuilder, superDropSetCommandStrategy());
    }
    
    @Bean("removesuperdrop")
    public PluginCommandExecutor superDropRemoveCommandExecutor(PersistentDataController.Builder superDropPlayerStateControllerBuilder) {
        return new PlayerStateCommandExecutor(superDropPlayerStateControllerBuilder, superDropRemoveCommandStrategy());
    }
    
    @Bean("issuperdrop")
    public PluginCommandExecutor superDropQueryCommandExecutor(PersistentDataController.Builder superDropPlayerStateControllerBuilder) {
        return new PlayerStateCommandExecutor(superDropPlayerStateControllerBuilder, superDropQueryCommandStrategy());
    }
    
    @Bean
    public PersistentDataController.Builder superDropPlayerStateControllerBuilder(JavaPlugin plugin) {
        return new PersistentDataController.Builder(plugin, "superdrop");
    }
    
    @Bean
    public ItemSpeeder itemSpeeder() {
        return new ItemSpeeder();
    }
    
    @Bean
    public SuperDropRemoveCommandStrategy superDropRemoveCommandStrategy() {
        return new SuperDropRemoveCommandStrategy();
    }
    
    @Bean
    public SuperDropQueryCommandStrategy superDropQueryCommandStrategy() {
        return new SuperDropQueryCommandStrategy();
    }
    
    @Bean
    public SuperDropSetCommandStrategy superDropSetCommandStrategy() {
        return new SuperDropSetCommandStrategy();
    }
}
