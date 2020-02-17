package com.gmail.noxdawn.taskattach;

import lombok.Data;

@Data
public class CoolDwonTask implements SelfStopTask {
    private final TryTask task;
    private final int cdTicks;
    private int counter = 0;
    
    @Override
    public void setSelfStopCallback(Runnable callback) {
    }
    
    @Override
    public void run() {
        if (counter == 0) {
            task.run();
            if (task.isSuccesss()) {
                counter = cdTicks;
            }
        } else {
            --counter;
        }
    }
}
