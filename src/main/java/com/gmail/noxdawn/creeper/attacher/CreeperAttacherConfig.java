package com.gmail.noxdawn.creeper.attacher;

import com.gmail.noxdawn.taskattach.GenericTaskAttacher;
import com.gmail.noxdawn.taskattach.GenericTaskDettacher;
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
    public AttachPlayerCommand attachPlayerCommand(GenericTaskAttacher<? super Player> attacher) {
        return new AttachPlayerCommand(attacher);
    }
    
    @Bean("uncreeper")
    public DettachPlayerCommand dettachPlayerCommand(GenericTaskDettacher<? super Player> dettacher) {
        return new DettachPlayerCommand(dettacher);
    }
    
    @Bean
    public GenericTaskAttacher<Entity> entityTaskAttacher(CustomScheduler scheduler, GenericTaskDettacher<Entity> dettacher) {
        return new GenericTaskAttacher<Entity>(scheduler, dettacher) {
            @Override
            public SelfStopTask getTask(Entity target) {
                return new CreeperSpawner(target);
            }
        };
    }
    
    @Bean
    public CustomScheduler customScheduler(BukkitScheduler scheduler, JavaPlugin plugin) {
        return new CustomScheduler(scheduler, plugin);
    }
    
    @Bean
    public GenericTaskDettacher<Entity> entityTaskDettacher() {
        return new GenericTaskDettacher<>(new HashMap<Entity, GenericTaskDettacher.TaskSuite>());
    }
}
