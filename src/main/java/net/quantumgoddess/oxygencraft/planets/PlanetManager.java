package net.quantumgoddess.oxygencraft.planets;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class PlanetManager {

    public static final HashMap<Identifier, RegistryKey<World>> planets = new HashMap<>();


    public static void teleport(World world, Entity entity, Identifier planetID) {
        // if (link == null) return;
        // if (link.getBeforeTPEvent().execute(entity) == SHOULDTP.CANCEL_TP)
        //     return;
        RegistryKey<World> destKey = PlanetManager.planets.get(planetID);
        ServerWorld destination = ((ServerWorld) world).getServer().getWorld(destKey);
        if (destination == null) return;
        if(!entity.canUsePortals())return;

        //destination.getChunkManager().addTicket(ChunkTicketType.PORTAL,new ChunkPos(new BlockPos(portalPos.getX()/destination.getDimension().coordinateScale(),portalPos.getY()/destination.getDimension().coordinateScale(),portalPos.getZ()/destination.getDimension().coordinateScale())), 3, new BlockPos(portalPos.getX()/destination.getDimension().coordinateScale(),portalPos.getY()/destination.getDimension().coordinateScale(),portalPos.getZ()/destination.getDimension().coordinateScale()));
        //TeleportTarget target = customTPTarget(destination, entity, portalPos, portalBase, link.getFrameTester());
        //((CustomTeleportingEntity) entity).setCustomTeleportTarget(target);
        ((ServerPlayerEntity)entity).teleport(destination, destination.getSpawnPos().getX(), destination.getSpawnPos().getY(), destination.getSpawnPos().getZ(), entity.getHeadYaw(), entity.getPitch());
        // if (entity != null) {
        //     entity.setYaw(target.yaw);
        //     entity.setPitch(target.pitch);
        //     if (entity instanceof ServerPlayerEntity)
        //         entity.refreshPositionAfterTeleport(target.position);
        //     link.executePostTPEvent(entity);
        // }
    }
}
