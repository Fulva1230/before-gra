package com.gmail.noxdawn.creeper.sound;

import com.gmail.noxdawn.PluginCommandExecutor;
import com.gmail.noxdawn.taskattach.TryTask;
import lombok.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Data
public abstract class PlaySoundCommand implements PluginCommandExecutor {
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
            TryTask tryTask = getTask(targetPlayer);
            tryTask.run();
        }
        return true;
    }
    
    public abstract TryTask getTask(Player player);
}
