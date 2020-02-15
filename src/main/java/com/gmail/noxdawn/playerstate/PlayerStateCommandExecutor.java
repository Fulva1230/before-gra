package com.gmail.noxdawn.playerstate;

import com.gmail.noxdawn.PluginCommandExecutor;
import lombok.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class PlayerStateCommandExecutor implements PluginCommandExecutor {
    private final PersistentDataController.Builder playerStateControllerBuilder;
    private final Consumer<Context> strategy;
    
    public PlayerStateCommandExecutor(PersistentDataController.Builder playerStateControllerBuilder, Consumer<Context> strategy) {
        this.playerStateControllerBuilder = playerStateControllerBuilder;
        this.strategy = strategy;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            Player player = sender.getServer().getPlayer(args[0]);
            if (player != null) {
                final PersistentDataController persistentDataController = playerStateControllerBuilder.build(player);
                strategy.accept(new Context(persistentDataController, player));
            }
        }
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            final PersistentDataController persistentDataController = playerStateControllerBuilder.build(player);
            strategy.accept(new Context(persistentDataController, player));
        }
        return true;
    }
    
    @Data
    public static class Context {
        private final PersistentDataController persistentDataController;
        private final Player player;
    }
}
