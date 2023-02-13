package net.dachi.dream.keybind;

import net.dachi.dream.Dream;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.KeyEvent;



public class ModKeybinding  {

    public static KeyBinding dreamKey;
    public static ServerPlayerEntity serverPlayerEntity;

    public static void register(final FMLClientSetupEvent event) {
        dreamKey = create("dream_key", KeyEvent.VK_K);

        ClientRegistry.registerKeyBinding(dreamKey);
    }

    private static KeyBinding create(String name, int key) {
        return new KeyBinding("key." + Dream.MOD_ID + "." + name, key, "key.category." + Dream.MOD_ID);
    }








}
