package com.gmail.noxdawn.superdrop;

import com.gmail.noxdawn.playerstate.PlayerStateCommandExecutor;

import java.util.function.Consumer;

public class SuperDropQueryCommandStrategy implements Consumer<PlayerStateCommandExecutor.Context> {
    private final static String INFO_MESSAGE_POSITIVE = "You have superdrop";
    private final static String INFO_MESSAGE_NEGAIVE = "You don't have superdrop";
    
    @Override
    public void accept(PlayerStateCommandExecutor.Context context) {
        if (context.getPersistentDataController().isTagged()) {
            context.getPlayer().sendMessage(INFO_MESSAGE_POSITIVE);
        } else {
            context.getPlayer().sendMessage(INFO_MESSAGE_NEGAIVE);
        }
    }
}
