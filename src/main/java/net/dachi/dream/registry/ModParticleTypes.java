package net.dachi.dream.registry;

import net.dachi.dream.Dream;
import net.dachi.dream.particle.custom.DreamParticle;
import net.dachi.dream.particle.data.DreamParticleData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Dream.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleTypes {
    //public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Dream.MOD_ID);
//
    //public static final RegistryObject<BasicParticleType> DREAM_AMBIENT_PARTICLES = PARTICLES.register("dream_ambient_particles", () -> new BasicParticleType(false));
//
    //@SubscribeEvent
    //@OnlyIn(Dist.CLIENT)
    //public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
    //    ParticleManager particleManager = Minecraft.getInstance().particles;
//
    //    //particleManager.registerFactory(DREAM_AMBIENT_PARTICLES.get(), p_create_1_ -> DreamParticle);
    //}
}
