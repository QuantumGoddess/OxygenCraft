package net.quantumgoddess.oxygencraft.planets;

import net.minecraft.server.world.ServerWorld;

public class Planet {
    
    private ServerWorld world;
    private boolean hasOxygen;
    

    public Planet(ServerWorld world, boolean hasOxygen) {
        this.world = world;
        this.hasOxygen = hasOxygen;
    }

    public boolean hasOxygen() {
        return hasOxygen;
    }

    public void setOxygen(boolean hasOxygen) {
        this.hasOxygen = hasOxygen;
    }

    public ServerWorld getServerWorld() {
        return world;
    }
    
}
