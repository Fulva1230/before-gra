package com.gmail.noxdawn.nofallarrow;

import com.gmail.noxdawn.playerstate.PlayerStateController;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.function.Consumer;

public class LaunchArrowListener implements Listener {
    private final PlayerStateController.Builder playerStateControllerBuilder;
    private final Consumer<AbstractArrow> arrowModifier;
    
    public LaunchArrowListener(PlayerStateController.Builder playerStateControllerBuilder, Consumer<AbstractArrow> arrowModifier) {
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
            final PlayerStateController playerStateController = playerStateControllerBuilder.build(player);
            if (playerStateController.isTagged()) {
                arrowModifier.accept(abstractArrow);
            }
        }
    }
}
