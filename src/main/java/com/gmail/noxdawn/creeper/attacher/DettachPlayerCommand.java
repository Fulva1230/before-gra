package com.gmail.noxdawn.creeper.attacher;

import com.gmail.noxdawn.PluginCommandExecutor;
import com.gmail.noxdawn.taskattach.GenericTaskManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DettachPlayerCommand implements PluginCommandExecutor {
    private static final String INFO_MESSAGE = "You save a player from creeper's attack";
    private final GenericTaskManager<? super Player> genericTaskDettacher;
    
    public DettachPlayerCommand(GenericTaskManager<? super Player> genericTaskDettacher) {
        this.genericTaskDettacher = genericTaskDettacher;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            Player player = sender.getServer().getPlayer(args[0]);
            if (player != null) {
                genericTaskDettacher.dettach(player);
                sender.sendMessage(INFO_MESSAGE);
                return true;
            }
        }
        return false;
    }
}
