package com.gmail.noxdawn.taskattach;

import lombok.Data;

@Data
public class TryUntilSuccessTask implements SelfStopTask {
    private final TryTask tryTask;
    private Runnable stopCallback = null;
    
    
    @Override
    public void setSelfStopCallback(Runnable callback) {
        stopCallback = callback;
    }
    
    @Override
    public void run() {
        if (stopCallback != null) {
            tryTask.run();
            if (tryTask.isSuccesss()) {
                stopCallback.run();
            }
        }
    }
}
