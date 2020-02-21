package com.gmail.noxdawn.taskattach;

public interface GenericTaskManager<T> {
    void attach(T target);
    
    boolean dettach(T target);
}
