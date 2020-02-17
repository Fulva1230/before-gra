package com.gmail.noxdawn;

import lombok.Data;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Properties;
import java.util.Set;
import java.util.function.Supplier;

@Data
public class PropertyConverter implements Supplier<Properties> {
    private final FileConfiguration configuration;
    
    @Override
    public Properties get() {
        Set<String> keys = configuration.getKeys(true);
        Properties properties = new Properties();
        for (String key : keys) {
            properties.setProperty(key, configuration.getString(key));
        }
        return properties;
    }
}
