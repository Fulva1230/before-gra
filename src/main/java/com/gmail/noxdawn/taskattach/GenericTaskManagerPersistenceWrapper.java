package com.gmail.noxdawn.taskattach;

import com.gmail.noxdawn.Disabler;
import com.gmail.noxdawn.Enabler;
import lombok.Data;

import java.io.*;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

@Data
public class GenericTaskManagerPersistenceWrapper<T> implements GenericTaskManager<T>, Enabler, Disabler {
    private final GenericTaskManagerImp<T> genericTaskManagerImp;
    private final File saveFile;
    private Set<T> tSet = new HashSet<>();
    
    @Override
    public void attach(T target) {
        genericTaskManagerImp.attach(target);
    }
    
    @Override
    public boolean dettach(T target) {
        genericTaskManagerImp.dettach(target);
    }
    
    @Override
    public void disable() {
        try (OutputStream fileOutputSteam = Files.newOutputStream()) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputSteam)) {
                objectOutputStream.writeObject(tSet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void enable() {
        try (InputStream fileInputStream = new FileInputStream(saveFile)) {
            try (ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
                tSet = (Set<T>) inputStream.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
