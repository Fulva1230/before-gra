package com.gmail.noxdawn.creeper.sound;

import com.gmail.noxdawn.taskattach.TryTask;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
public class SoundPlayAlgo implements TryTask {
    private final UUID playerId;
    private final Server server;
    private boolean successFlag = false;
    
    @Override
    public boolean isSuccesss() {
        return successFlag;
    }
    
    @Override
    public void run() {
        successFlag = false;
        Player onlinePlayer = server.getPlayer(playerId);
        if (onlinePlayer != null) {
            Location playerLocation = onlinePlayer.getLocation();
            Location backLocation = playerLocation.add(playerLocation.getDirection().setY(0).multiply(-1));
            if (backLocation.getBlock().isEmpty() && backLocation.getBlock().getLightLevel() < 5) {
                onlinePlayer.playSound(backLocation, Sound.ENTITY_CREEPER_PRIMED, 10, 0);
                successFlag = true;
            }
        }
    }
}
