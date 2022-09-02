package net.quantumgoddess.oxygencraft;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.quantumgoddess.oxygencraft.blocks.OCBlocks;
import net.quantumgoddess.oxygencraft.items.OCItems;
import net.quantumgoddess.oxygencraft.planets.Planet;
import net.quantumgoddess.oxygencraft.planets.PlanetManager;

public class OCMain implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("oxygencraft");
	private static KeyBinding keyBinding;

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
        new Identifier("oxygencraft", "general"),
        () -> new ItemStack(Blocks.COBBLESTONE));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//register all items
		OCItems.register();
		//register all blocks
		OCBlocks.register();

		
		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.examplemod.spook", // The translation key of the keybinding's name
            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
            GLFW.GLFW_KEY_R, // The keycode of the key
            "category.examplemod.test" // The translation key of the keybinding's category.
        ));

        ServerTickEvents.END_WORLD_TICK.register(world -> {
            while (keyBinding.wasPressed()) {
            //server. .sendMessage(Text.literal("Key 1 was pressed!"), false);
                PlanetManager.teleport(world.getPlayerByUuid(OCClient.MCClient.player.getUuid()), PlanetManager.planets.get(World.NETHER).getServerWorld());
            }
        });




		//register planets and overworld
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            for (RegistryKey<World> registryKey : server.getWorldRegistryKeys()) {
				LOGGER.info(registryKey + "");
				if(registryKey.getValue().getPath().equals("overworld") || registryKey.getValue().getNamespace().equals("oxygencraft") || registryKey.getValue().getPath().equals("the_nether"))
                	PlanetManager.planets.put(registryKey, new Planet(server.getWorld(registryKey), registryKey.getValue().getPath().equals("overworld") ? true : false));
            }
		});

		LOGGER.info("Hello Fabric world!");


	}
}
