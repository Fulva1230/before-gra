package com.gmail.noxdawn.superdrop;

import com.gmail.noxdawn.playerstate.PersistentDataController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {
    private final PersistentDataController.Builder playerStateControllerBuilder;
    private final ItemSpeeder itemSpeeder;
    
    public DropListener(PersistentDataController.Builder playerStateControllerBuilder, ItemSpeeder itemSpeeder) {
        this.playerStateControllerBuilder = playerStateControllerBuilder;
        this.itemSpeeder = itemSpeeder;
    }
    
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        PersistentDataController persistentDataController = playerStateControllerBuilder.build(player);
        if (persistentDataController.isTagged()) {
            itemSpeeder.accept(event.getItemDrop());
        }
    }
}
