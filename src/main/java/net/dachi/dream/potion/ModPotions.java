package net.dachi.dream.potion;

import net.dachi.dream.Dream;
import net.dachi.dream.effects.ModEffects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Dream.MOD_ID);

    public static final RegistryObject<Potion> DREAM_POTION = POTIONS.register("dream_potion",
            () -> new Potion(new EffectInstance(ModEffects.DREAM.get(), 200, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
