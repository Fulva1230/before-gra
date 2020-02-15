package com.gmail.noxdawn.taskattach;

public interface SelfStopTask extends Runnable {
    void setSelfStopCallback(Runnable callback);
}
