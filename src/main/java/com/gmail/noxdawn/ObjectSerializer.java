package com.gmail.noxdawn;

import lombok.Data;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Data
public class ObjectSerializer {
    private final JavaPlugin javaPlugin;
    private final String path;
    
    public void save(Object object) {
        try (OutputStream fileOutputSteam = Files.newOutputStream(javaPlugin.getDataFolder().toPath().resolve(path), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputSteam)) {
                objectOutputStream.writeObject(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Optional<Object> load() {
        try (InputStream fileInputStream = Files.newInputStream(javaPlugin.getDataFolder().toPath().resolve(path), StandardOpenOption.READ)) {
            try (ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
                return Optional.of(inputStream.readObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ignored) {
        }
        return Optional.empty();
    }
}
