package com.gmail.noxdawn.playerstate;

import com.gmail.noxdawn.PluginCommandExecutor;
import lombok.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class PlayerStateCommandExecutor implements PluginCommandExecutor {
    private final PlayerStateController.Builder playerStateControllerBuilder;
    private final Consumer<Context> strategy;
    
    public PlayerStateCommandExecutor(PlayerStateController.Builder playerStateControllerBuilder, Consumer<Context> strategy) {
        this.playerStateControllerBuilder = playerStateControllerBuilder;
        this.strategy = strategy;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            final PlayerStateController playerStateController = playerStateControllerBuilder.build(player);
            strategy.accept(new Context(playerStateController, player));
        }
        return true;
    }
    
    @Data
    public static class Context {
        private final PlayerStateController playerStateController;
        private final Player player;
    }
}
