package net.dachi.dream.entity;

import net.dachi.dream.Dream;
import net.dachi.dream.entity.custom.NightmareBlazeEntity;
import net.dachi.dream.entity.custom.PenguinEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Dream.MOD_ID);

    public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN =
            ENTITY_TYPES.register("penguin",
                    () -> EntityType.Builder.create(PenguinEntity::new,
                                    EntityClassification.CREATURE).size(0.5f, 1f)
                            .build(new ResourceLocation(Dream.MOD_ID, "penguin").toString()));

    public static final RegistryObject<EntityType<NightmareBlazeEntity>> NIGHTMARE_BLAZE =
            ENTITY_TYPES.register("nightmare_blaze",
                    () -> EntityType.Builder.create(NightmareBlazeEntity::new,
                                    EntityClassification.MONSTER).size(0.5f, 2f)
                            .build(new ResourceLocation(Dream.MOD_ID, "nightmare_blaze").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
