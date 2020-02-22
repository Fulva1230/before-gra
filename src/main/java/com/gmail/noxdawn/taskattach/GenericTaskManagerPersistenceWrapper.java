package com.gmail.noxdawn.taskattach;

import com.gmail.noxdawn.Disabler;
import com.gmail.noxdawn.Enabler;
import com.gmail.noxdawn.ObjectSerializer;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class GenericTaskManagerPersistenceWrapper<T> implements GenericTaskManager<T>, Enabler, Disabler {
    private final GenericTaskManagerImp<T> genericTaskManagerImp;
    private final ObjectSerializer objectSerializer;
    private Set<T> tSet = new HashSet<>();
    
    @Override
    public void attach(T target) {
        tSet.add(target);
        genericTaskManagerImp.attach(target);
    }
    
    @Override
    public boolean dettach(T target) {
        tSet.remove(target);
        return genericTaskManagerImp.dettach(target);
    }
    
    @Override
    public void disable() {
        objectSerializer.save(tSet);
    }
    
    @Override
    public void enable() {
        objectSerializer.load().ifPresent(
                (object) -> {
                    tSet = (Set<T>) object;
                }
        );
        for (T entry : tSet) {
            genericTaskManagerImp.attach(entry);
        }
    }
}
