package com.gmail.noxdawn.superdrop;

import com.gmail.noxdawn.playerstate.PlayerStateCommandExecutor;

import java.util.function.Consumer;

public class SuperDropSetCommandStrategy implements Consumer<PlayerStateCommandExecutor.Context> {
    private final static String INFO_MESSAGE = "You have superdorp";
    
    @Override
    public void accept(PlayerStateCommandExecutor.Context context) {
        context.getPersistentDataController().setTagged();
        context.getPlayer().sendMessage(INFO_MESSAGE);
    }
}
