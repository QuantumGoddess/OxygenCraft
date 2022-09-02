package net.quantumgoddess.oxygencraft;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class OCClient implements ClientModInitializer{

    public static MinecraftClient MCClient;

    @Override
    public void onInitializeClient() {
        MCClient = MinecraftClient.getInstance();
    }


    
}
