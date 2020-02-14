package com.gmail.noxdawn.nofallarrow;

import org.bukkit.entity.AbstractArrow;

import java.util.function.Consumer;

public class NoFallArrowConsumer implements Consumer<AbstractArrow> {
    @Override
    public void accept(AbstractArrow abstractArrow) {
        abstractArrow.setGravity(false);
    }
}
