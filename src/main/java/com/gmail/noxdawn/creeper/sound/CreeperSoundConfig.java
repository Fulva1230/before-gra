package com.gmail.noxdawn.creeper.sound;

import com.gmail.noxdawn.ObjectSerializer;
import com.gmail.noxdawn.command.template.PlayerTargetCommand;
import com.gmail.noxdawn.taskattach.*;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.UUID;

@Configuration
public class CreeperSoundConfig {
    @Bean("playcreeper")
    public PlaySoundCommand playSoundCommand(Server server) {
        return new PlaySoundCommand() {
            @Override
            public TryTask getTask(Player player) {
                return new SoundPlayAlgo(player.getUniqueId(), server);
            }
        };
    }
    
    @Bean("playcreeperattach")
    public PlayerTargetCommand creeperSoundTaskAttachCommand(GenericTaskManager<UUID> creeperSoundTaskManager) {
        return new PlayerTargetCommand((player) -> {
            creeperSoundTaskManager.attach(player.getUniqueId());
        });
    }
    
    @Bean("playcreeperdettach")
    public PlayerTargetCommand creeperSoundTaskDettachCommand(GenericTaskManager<UUID> creeperSoundTaskManager) {
        return new PlayerTargetCommand((player) -> {
            creeperSoundTaskManager.dettach(player.getUniqueId());
        });
    }
    
    @Bean
    public GenericTaskManagerImp<UUID> creeperSoundTaskManagerImp(CustomScheduler scheduler, Server server, @Value("${creepersound.cd-ticks}") int cdTicks) {
        return new GenericTaskManagerImp<UUID>(scheduler, new HashMap<>()) {
            @Override
            public SelfStopTask getTask(UUID target) {
                return new CoolDwonTask(new SoundPlayAlgo(target, server), cdTicks);
            }
        };
    }
    
    @Bean
    public GenericTaskManagerPersistenceWrapper<UUID> creeperSoundTaskManager(GenericTaskManagerImp<UUID> creeperSoundTaskManagerImp, ObjectSerializer objectSerializer) {
        return new GenericTaskManagerPersistenceWrapper<>(creeperSoundTaskManagerImp, objectSerializer);
    }
    
    @Bean
    public ObjectSerializer objectSerializer(JavaPlugin javaPlugin, @Value("${creepersound.path}") String path) {
        return new ObjectSerializer(javaPlugin, path);
    }
}
