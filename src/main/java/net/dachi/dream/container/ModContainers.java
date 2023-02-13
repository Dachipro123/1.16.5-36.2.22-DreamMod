package net.dachi.dream.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.dachi.dream.Dream;

public class ModContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, Dream.MOD_ID);

    public static final RegistryObject<ContainerType<DreamBlasterContainer>> DREAM_BLASTER_CONTAINER
            = CONTAINERS.register("dream_blaster_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new DreamBlasterContainer(windowId, world, pos, inv, inv.player);
            })));


    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}