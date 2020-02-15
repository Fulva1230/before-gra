package com.gmail.noxdawn.creeper.attacher;

import com.gmail.noxdawn.PluginCommandExecutor;
import com.gmail.noxdawn.taskattach.GenericTaskAttacher;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AttachPlayerCommand implements PluginCommandExecutor {
    private final static String INFO_MESSAGE = "You have attach creeper to someone";
    private final GenericTaskAttacher<? super Player> attacher;
    
    public AttachPlayerCommand(GenericTaskAttacher<? super Player> attacher) {
        this.attacher = attacher;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            Player player = sender.getServer().getPlayer(args[0]);
            if (player != null) {
                attacher.attach(player);
                sender.sendMessage(INFO_MESSAGE);
                return true;
            }
        }
        return false;
    }
}
