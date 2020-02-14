package com.gmail.noxdawn.superdrop;

import com.gmail.noxdawn.playerstate.PlayerStateController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {
    private final PlayerStateController.Builder playerStateControllerBuilder;
    private final ItemSpeeder itemSpeeder;
    
    public DropListener(PlayerStateController.Builder playerStateControllerBuilder, ItemSpeeder itemSpeeder) {
        this.playerStateControllerBuilder = playerStateControllerBuilder;
        this.itemSpeeder = itemSpeeder;
    }
    
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        PlayerStateController playerStateController = playerStateControllerBuilder.build(player);
        if (playerStateController.isTagged()) {
            itemSpeeder.accept(event.getItemDrop());
        }
    }
}
