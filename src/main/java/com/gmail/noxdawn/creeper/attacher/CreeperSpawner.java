package com.gmail.noxdawn.creeper.attacher;

import com.gmail.noxdawn.taskattach.SelfStopTask;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

public class CreeperSpawner implements SelfStopTask {
    private final Entity entity;
    private Runnable stopCallback = () -> {
    };
    
    public CreeperSpawner(Entity entity) {
        this.entity = entity;
    }
    
    private boolean checkBlockFeasible(Block block) {
        
        return block.isEmpty() &&
                !block.getLocation().add(new Vector(0, -1, 0)).getBlock().isEmpty() &&
                block.getLocation().add(new Vector(0, 1, 0)).getBlock().isEmpty();
    }
    
    @Override
    public void run() {
        final Location center = entity.getLocation();
        final Vector searchdir = entity.getLocation().getDirection().setY(0).multiply(-1);
        if (searchdir.length() > 0.1) {
            searchdir.normalize();
        }
        for (int i = 4; i < 7; ++i) {
            Location checkPoint = center.clone().add(searchdir.clone().multiply(i));
            if (checkPoint.getBlock().getLightLevel() < 8 && checkBlockFeasible(checkPoint.getBlock())
            ) {
                checkPoint.getWorld().spawnEntity(checkPoint, EntityType.CREEPER);
                stopCallback.run();
                return;
            }
        }
    }
    
    @Override
    public void setSelfStopCallback(Runnable callback) {
        stopCallback = callback;
    }
}
