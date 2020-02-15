package com.gmail.noxdawn.taskattach;

import lombok.Data;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;

public class GenericTaskDettacher<T> {
    private final Map<T, TaskSuite> taskMap;
    
    public GenericTaskDettacher(Map<T, TaskSuite> taskMap) {
        this.taskMap = taskMap;
    }
    
    public boolean dettach(T target) {
        if (taskMap.containsKey(target)) {
            taskMap.get(target).taskHandler.cancel();
            taskMap.remove(target);
            return true;
        }
        return false;
    }
    
    public void addTrack(T target, TaskSuite taskSuite) {
        taskSuite.getTask().setSelfStopCallback(() -> dettach(target));
        taskMap.put(target, taskSuite);
    }
    
    @Data
    public static class TaskSuite {
        private final BukkitTask taskHandler;
        private final SelfStopTask task;
    }
}
