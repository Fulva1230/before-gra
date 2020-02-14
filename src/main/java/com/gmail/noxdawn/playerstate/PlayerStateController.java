package com.gmail.noxdawn.playerstate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerStateController {
    private final static PersistentDataType DATA_TYPE = PersistentDataType.STRING;
    private final static String DUMMY_STRING = "hehe";
    private final Player player;
    private final NamespacedKey key;
    
    public PlayerStateController(Player player, NamespacedKey key) {
        this.player = player;
        this.key = key;
    }
    
    public boolean isTagged() {
        return player.getPersistentDataContainer().has(key, DATA_TYPE);
    }
    
    public void setTagged() {
        player.getPersistentDataContainer().set(key, DATA_TYPE, DUMMY_STRING);
    }
    
    public void setUnTagged() {
        player.getPersistentDataContainer().remove(key);
    }
    
    
    public static class Builder {
        private final NamespacedKey key;
        
        public Builder(JavaPlugin plugin, String keyString) {
            key = new NamespacedKey(plugin, keyString);
        }
        
        public PlayerStateController build(Player player) {
            return new PlayerStateController(player, key);
        }
    }
}
