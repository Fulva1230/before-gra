package com.gmail.noxdawn.funcommands;

import com.gmail.noxdawn.PluginCommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class EchoFuckExecutor implements PluginCommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("fuck");
        return true;
    }
}
