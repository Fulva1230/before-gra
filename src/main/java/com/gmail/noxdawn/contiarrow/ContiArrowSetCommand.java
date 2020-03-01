package com.gmail.noxdawn.contiarrow;

import com.gmail.noxdawn.PluginCommandExecutor;
import lombok.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Data
public class ContiArrowSetCommand implements PluginCommandExecutor {
    private final ContiArrowItemStackUtil contiArrowItemStackUtil;
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            contiArrowItemStackUtil.setTagged(itemStack);
            player.sendMessage("successfully set item in your hand");
            return true;
        }
        return false;
    }
}
