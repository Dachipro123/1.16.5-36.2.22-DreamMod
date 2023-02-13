package net.dachi.dream.effects;

import net.dachi.dream.Dream;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects {
    public static final DeferredRegister<Effect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.POTIONS, Dream.MOD_ID);

    public static final RegistryObject<Effect> DREAM = MOB_EFFECTS.register("dream",
            () -> new FreezeEffect(EffectType.HARMFUL, 3124687));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
