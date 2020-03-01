package com.gmail.noxdawn.contiarrow;

import com.gmail.noxdawn.playerstate.PersistentDataController;
import lombok.Data;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Data
public class ContiArrowItemStackUtil {
    private final PersistentDataController.Builder persistentDataControllerBuilder;
    
    public boolean isTagged(ItemStack itemStack) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            final PersistentDataController persistentDataController = persistentDataControllerBuilder.build(itemMeta);
            return persistentDataController.isTagged();
        }
        return false;
    }
    
    public void setTagged(ItemStack itemStack) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            final PersistentDataController persistentDataController = persistentDataControllerBuilder.build(itemMeta);
            persistentDataController.setTagged();
        }
        itemStack.setItemMeta(itemMeta);
    }
}
