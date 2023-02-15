package net.dachi.dream.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.dachi.dream.Dream;
import net.dachi.dream.dreamy.PlayerSleepiness;
import net.dachi.dream.dreamy.PlayerSleepinessProvider;
import net.dachi.dream.item.ModItems;
import net.dachi.dream.villager.ModVillagers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = Dream.MOD_ID)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {

    }
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.DREAM_MAKER.get()) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.DREAM_INGOT.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    stack,20,12,0.09F));
        }
        if(event.getType() == ModVillagers.DREAM_MAKER.get()) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.DREAM_SWORD.get(), 1);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 64),
                    stack,1,120,0.09F));
        }
    }
    //@SubscribeEvent
    //public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
    //    Minecraft.getInstance().particles.registerFactory(ModParticles.DREAM_AMBIENT_PARTICLES.get(),
    //            DreamParticle.Factory::new);
    //}
    //@SubscribeEvent
    //public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
    //    if(event.getObject() instanceof PlayerEntity) {
    //        if(!event.getObject().getCapability(PlayerSleepinessProvider.PLAYER_SLEEPINESS).isPresent()) {
    //            event.addCapability(new ResourceLocation(Dream.MOD_ID, "properties"), new PlayerSleepinessProvider());
    //        }
    //    }
    //}
//
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerSleepinessProvider.PLAYER_SLEEPINESS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerSleepinessProvider.PLAYER_SLEEPINESS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }


}