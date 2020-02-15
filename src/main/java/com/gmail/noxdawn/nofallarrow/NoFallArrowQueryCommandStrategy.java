package com.gmail.noxdawn.nofallarrow;

import com.gmail.noxdawn.playerstate.PlayerStateCommandExecutor;

import java.util.function.Consumer;

public class NoFallArrowQueryCommandStrategy implements Consumer<PlayerStateCommandExecutor.Context> {
    private static final String POSITIVE_INFO_MESSAGE = "You have no fall arrow";
    private static final String NEGATIVE_INFO_MESSAGE = "You don't have no fall arrow";
    
    @Override
    public void accept(PlayerStateCommandExecutor.Context context) {
        if (context.getPersistentDataController().isTagged()) {
            context.getPlayer().sendMessage(POSITIVE_INFO_MESSAGE);
        } else {
            context.getPlayer().sendMessage(NEGATIVE_INFO_MESSAGE);
        }
    }
}
