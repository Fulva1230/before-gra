package com.gmail.noxdawn.nofallarrow;

import com.gmail.noxdawn.playerstate.PersistentDataController;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.function.Consumer;

public class LaunchArrowListener implements Listener {
    private final PersistentDataController.Builder playerStateControllerBuilder;
    private final Consumer<AbstractArrow> arrowModifier;
    
    public LaunchArrowListener(PersistentDataController.Builder playerStateControllerBuilder, Consumer<AbstractArrow> arrowModifier) {
        this.playerStateControllerBuilder = playerStateControllerBuilder;
        this.arrowModifier = arrowModifier;
    }
    
    
    @EventHandler
    public void onArrowLaunch(ProjectileLaunchEvent event) {
        final Projectile projectile = event.getEntity();
        final ProjectileSource projectileSource = projectile.getShooter();
        if (projectileSource instanceof Player && projectile instanceof AbstractArrow) {
            final AbstractArrow abstractArrow = (AbstractArrow) projectile;
            final Player player = (Player) projectileSource;
            final PersistentDataController persistentDataController = playerStateControllerBuilder.build(player);
            if (persistentDataController.isTagged()) {
                arrowModifier.accept(abstractArrow);
            }
        }
    }
}
