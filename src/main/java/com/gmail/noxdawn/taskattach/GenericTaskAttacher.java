package com.gmail.noxdawn.taskattach;

import com.gmail.noxdawn.creeper.attacher.CustomScheduler;
import org.bukkit.scheduler.BukkitTask;

public abstract class GenericTaskAttacher<T> {
    private final CustomScheduler scheduler;
    private final GenericTaskDettacher<T> dettacher;
    
    public GenericTaskAttacher(CustomScheduler scheduler, GenericTaskDettacher<T> dettacher) {
        this.scheduler = scheduler;
        this.dettacher = dettacher;
    }
    
    public void attach(T target) {
        SelfStopTask selfStopTask = getTask(target);
        final BukkitTask bukkitTask = scheduler.runTask(selfStopTask);
        dettacher.addTrack(target, new GenericTaskDettacher.TaskSuite(bukkitTask, selfStopTask));
    }
    
    public abstract SelfStopTask getTask(T target);
}
