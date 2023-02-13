package net.dachi.dream.paintings;

import net.dachi.dream.Dream;
import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPaintings {
    public static final DeferredRegister<PaintingType> PAINTING_TYPES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Dream.MOD_ID);

    public static final RegistryObject<PaintingType> DREAM =
            PAINTING_TYPES.register("dream", () -> new PaintingType(16, 32));

    public static final RegistryObject<PaintingType> BOY =
            PAINTING_TYPES.register("boy", () -> new PaintingType(16, 32));


    public static void register(IEventBus eventBus) {
        PAINTING_TYPES.register(eventBus);
    }
}
