package com.gmail.noxdawn.nofallarrow;

import com.gmail.noxdawn.playerstate.PlayerStateCommandExecutor;

import java.util.function.Consumer;

public class NoFallArrowSetCommandStrategy implements Consumer<PlayerStateCommandExecutor.Context> {
    private static final String INFO_MESSAGE = "You have no fall arrow";
    
    @Override
    public void accept(PlayerStateCommandExecutor.Context context) {
        context.getPersistentDataController().setTagged();
        context.getPlayer().sendMessage(INFO_MESSAGE);
    }
}
