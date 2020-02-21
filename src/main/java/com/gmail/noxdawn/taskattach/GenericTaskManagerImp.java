package com.gmail.noxdawn.taskattach;

import lombok.Data;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;

@Data
public abstract class GenericTaskManagerImp<T> implements GenericTaskManager<T> {
    private final CustomScheduler scheduler;
    private final Map<T, TaskSuite> taskMap;
    
    @Override
    public void attach(T target) {
        if (!taskMap.containsKey(target)) {
            SelfStopTask selfStopTask = getTask(target);
            final BukkitTask bukkitTask = scheduler.runTask(selfStopTask);
            addTrack(target, new TaskSuite(bukkitTask, selfStopTask));
        }
    }
    
    private void addTrack(T target, TaskSuite taskSuite) {
        taskSuite.getTask().setSelfStopCallback(() -> dettach(target));
        taskMap.put(target, taskSuite);
    }
    
    @Override
    public boolean dettach(T target) {
        if (taskMap.containsKey(target)) {
            taskMap.get(target).taskHandler.cancel();
            taskMap.remove(target);
            return true;
        }
        return false;
    }
    
    public abstract SelfStopTask getTask(T target);
    
    @Data
    public static class TaskSuite {
        private final BukkitTask taskHandler;
        private final SelfStopTask task;
    }
}
