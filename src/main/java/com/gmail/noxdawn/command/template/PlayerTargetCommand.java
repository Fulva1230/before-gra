package com.gmail.noxdawn.command.template;

import com.gmail.noxdawn.PluginCommandExecutor;
import lombok.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

@Data
public class PlayerTargetCommand implements PluginCommandExecutor {
    private final Consumer<Player> playerConsumer;
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player targetPlayer = null;
        if (args.length == 1) {
            targetPlayer = sender.getServer().getPlayer(args[0]);
        } else {
            if (sender instanceof Player) {
                targetPlayer = (Player) sender;
            }
        }
        if (targetPlayer != null) {
            playerConsumer.accept(targetPlayer);
            return true;
        }
        return false;
    }
}
