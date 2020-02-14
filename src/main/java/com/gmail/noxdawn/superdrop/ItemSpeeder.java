package com.gmail.noxdawn.superdrop;

import org.bukkit.entity.Item;

import java.util.function.Consumer;

public class ItemSpeeder implements Consumer<Item> {
    @Override
    public void accept(Item item) {
        item.setVelocity(item.getVelocity().normalize().multiply(4));
    }
}