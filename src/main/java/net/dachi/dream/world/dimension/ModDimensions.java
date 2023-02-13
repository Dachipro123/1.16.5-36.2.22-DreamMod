package net.dachi.dream.world.dimension;

import net.dachi.dream.Dream;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;


public class ModDimensions{
    public static RegistryKey<World> DREAM;

    public static void register(IEventBus eventBus)
    {
        DREAM = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
                new ResourceLocation(Dream.MOD_ID, "dream"));
    }}







