package com.gmail.noxdawn.playerstate;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class PersistentDataController {
    private final static PersistentDataType<String, String> DATA_TYPE = PersistentDataType.STRING;
    private final static String DUMMY_STRING = "hehe";
    private final PersistentDataHolder persistentDataHolder;
    private final NamespacedKey key;
    
    public PersistentDataController(PersistentDataHolder persistentDataHolder, NamespacedKey key) {
        this.persistentDataHolder = persistentDataHolder;
        this.key = key;
    }
    
    public boolean isTagged() {
        return persistentDataHolder.getPersistentDataContainer().has(key, DATA_TYPE);
    }
    
    public void setTagged() {
        persistentDataHolder.getPersistentDataContainer().set(key, DATA_TYPE, DUMMY_STRING);
    }
    
    public void setUnTagged() {
        persistentDataHolder.getPersistentDataContainer().remove(key);
    }
    
    
    public static class Builder {
        private final NamespacedKey key;
        
        public Builder(JavaPlugin plugin, String keyString) {
            key = new NamespacedKey(plugin, keyString);
        }
        
        public PersistentDataController build(PersistentDataHolder persistentDataHolder) {
            return new PersistentDataController(persistentDataHolder, key);
        }
    }
}
