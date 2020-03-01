package com.gmail.noxdawn.contiarrow;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.logging.Logger;

@Data
public abstract class ContiArrowInteractListener implements Listener {
    private final Logger logger;
    private final ContiArrowItemStackUtil contiArrowItemStackUtil;
    
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        logger.log(logger.getLevel(), "on player interact event fire");
        final ItemStack eventItem = event.getItem();
        if (eventItem != null) {
            final boolean itemTagged = contiArrowItemStackUtil.isTagged(eventItem);
            logger.log(logger.getLevel(), String.valueOf(itemTagged));
            if (itemTagged) {
                playerConsumer().accept(event.getPlayer());
            }
        }
    }
    
    protected abstract Consumer<Player> playerConsumer();
}
