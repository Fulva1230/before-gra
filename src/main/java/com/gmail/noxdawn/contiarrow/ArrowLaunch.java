package com.gmail.noxdawn.contiarrow;

import lombok.Data;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

@Data
public class ArrowLaunch implements Consumer<Player> {
    private final JavaPlugin plugin;
    
    @Override
    public void accept(Player player) {
        BukkitRunnable bukkitRunnable = new BukkitRunnable() {
            private int count = 0;
            
            @Override
            public void run() {
                if (count < 3) {
                    ++count;
                    final AbstractArrow abstractArrow = player.launchProjectile(AbstractArrow.class);
                    abstractArrow.setDamage(9999d);
                    abstractArrow.setVelocity(abstractArrow.getVelocity().multiply(3));
                } else {
                    cancel();
                }
            }
        };
        bukkitRunnable.runTaskTimer(plugin, 0, 1);
    }
}
