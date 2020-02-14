package com.gmail.noxdawn.nofallarrow;

import com.gmail.noxdawn.playerstate.PlayerStateCommandExecutor;

import java.util.function.Consumer;

public class NoFallArrowRemoveCommandStrategy implements Consumer<PlayerStateCommandExecutor.Context> {
    private static final String INFO_MESSAGE = "You remove no fall arrow";
    
    @Override
    public void accept(PlayerStateCommandExecutor.Context context) {
        context.getPlayerStateController().setUnTagged();
        context.getPlayer().sendMessage(INFO_MESSAGE);
    }
}
