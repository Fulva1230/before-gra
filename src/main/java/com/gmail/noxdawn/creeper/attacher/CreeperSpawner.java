package com.gmail.noxdawn.creeper.attacher;

import com.gmail.noxdawn.taskattach.TryTask;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

public class CreeperSpawner implements TryTask {
    private final Entity entity;
    private boolean isSuccess = false;
    
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
        isSuccess = false;
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
                isSuccess = true;
                return;
            }
        }
    }
    
    @Override
    public boolean isSuccesss() {
        return isSuccess;
    }
}
