package com.gmail.noxdawn.creeper.attacher;

import com.gmail.noxdawn.taskattach.CustomScheduler;
import com.gmail.noxdawn.taskattach.GenericTaskManager;
import com.gmail.noxdawn.taskattach.SelfStopTask;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class CreeperAttacherConfig {
    @Bean("creeper")
    public AttachPlayerCommand attachPlayerCommand(GenericTaskManager<? super Player> creeperTaskManager) {
        return new AttachPlayerCommand(creeperTaskManager);
    }
    
    @Bean("uncreeper")
    public DettachPlayerCommand dettachPlayerCommand(GenericTaskManager<? super Player> creeperTaskManager) {
        return new DettachPlayerCommand(creeperTaskManager);
    }
    
    @Bean
    public CustomScheduler customScheduler(BukkitScheduler scheduler, JavaPlugin plugin) {
        return new CustomScheduler(scheduler, plugin);
    }
    
    @Bean
    GenericTaskManager<Entity> creeperTaskManager(CustomScheduler customScheduler) {
        return new GenericTaskManager<Entity>(customScheduler, new HashMap<>()) {
            @Override
            public SelfStopTask getTask(Entity target) {
                return new CreeperSpawner(target);
            }
        };
    }
}
