package net.dachi.dream.villager;

import com.google.common.collect.ImmutableSet;
import net.dachi.dream.Dream;
import net.dachi.dream.block.ModBlocks;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PointOfInterestType> POI_TYPES
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, Dream.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS
            = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Dream.MOD_ID);

    public static final RegistryObject<PointOfInterestType> DREAM_MAKER_POI = POI_TYPES.register("dream_maker_poi",
            () -> new PointOfInterestType("dream_maker_poi",
                    PointOfInterestType.getAllStates(ModBlocks.DREAM_BLOCK.get()), 1, 1));

    public static final RegistryObject<VillagerProfession> DREAM_MAKER =
            VILLAGER_PROFESSIONS.register("dream_maker",
                    () -> new VillagerProfession("dream_maker", DREAM_MAKER_POI.get(),
                            ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_WEAPONSMITH));

    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PointOfInterestType.class,
                    "registerBlockStates", PointOfInterestType.class).invoke(null, DREAM_MAKER_POI.get());
        } catch(InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
