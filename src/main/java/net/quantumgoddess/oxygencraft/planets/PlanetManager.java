package net.quantumgoddess.oxygencraft.planets;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;


public class PlanetManager {

    public static final Map<RegistryKey<World>, Planet> planets = Maps.newLinkedHashMap();


    public static void teleport(Entity entity, ServerWorld destination) {
        // if (link == null) return;
        // if (link.getBeforeTPEvent().execute(entity) == SHOULDTP.CANCEL_TP)
        //     return;
        if (entity == null) return;
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
