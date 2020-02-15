package com.gmail.noxdawn.superdrop;

import com.gmail.noxdawn.playerstate.PlayerStateCommandExecutor;

import java.util.function.Consumer;

public class SuperDropRemoveCommandStrategy implements Consumer<PlayerStateCommandExecutor.Context> {
    private final static String INFO_MESSAGE = "You don't have superdorp anymore";
    
    @Override
    public void accept(PlayerStateCommandExecutor.Context context) {
        context.getPersistentDataController().setUnTagged();
        context.getPlayer().sendMessage(INFO_MESSAGE);
    }
}
