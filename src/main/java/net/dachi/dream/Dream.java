package net.dachi.dream;

import com.google.common.collect.ImmutableMap;
import net.dachi.dream.block.ModBlocks;
import net.dachi.dream.container.ModContainers;
import net.dachi.dream.dreamy.PlayerSleepiness;
import net.dachi.dream.effects.ModEffects;
import net.dachi.dream.entity.ModEntityTypes;
import net.dachi.dream.entity.custom.NightmareBlazeEntity;
import net.dachi.dream.entity.custom.PenguinEntity;
import net.dachi.dream.entity.render.NightmareBlazeRenderer;
import net.dachi.dream.entity.render.PenguinRenderer;
import net.dachi.dream.fluid.ModFluids;
import net.dachi.dream.item.ModItems;
import net.dachi.dream.networking.ModMessages;
import net.dachi.dream.paintings.ModPaintings;
import net.dachi.dream.potion.ModPotions;
import net.dachi.dream.screen.DreamBlasterScreen;
import net.dachi.dream.tileentity.ModTileEntities;
import net.dachi.dream.util.ModSoundEvents;
import net.dachi.dream.villager.ModVillagers;
import net.dachi.dream.world.biome.ModBiomes;
import net.dachi.dream.world.dimension.ModDimensions;
import net.dachi.dream.world.gen.ModBiomeGeneration;
import net.dachi.dream.world.structure.ModStructures;
import net.dachi.dream.world.structure.ModdedStructures;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.AxeItem;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

import static org.apache.http.params.CoreProtocolPNames.PROTOCOL_VERSION;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Dream.MOD_ID)
public class Dream
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "dream";

    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("dream", "dream"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    public static RegistryKey<World> DREAM;


    public Dream() {
        // Register the setup method for modloading

        FMLJavaModLoadingContext.get().getModEventBus().register(this);

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);
        ModPaintings.register(eventBus);
        ModEffects.register(eventBus);
        ModPotions.register(eventBus);
        //ModParticles.register(eventBus);

        ModStructures.register(eventBus);
        ModdedStructures.register(eventBus);
        ModFluids.register(eventBus);
        ModSoundEvents.register(eventBus);
        ModEntityTypes.register(eventBus);
        ModBiomes.register(eventBus);
        ModDimensions.register(eventBus);
        ModVillagers.register(eventBus);





        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }




    private void setup(final FMLCommonSetupEvent event) {

        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntityTypes.PENGUIN.get(), PenguinEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.NIGHTMARE_BLAZE.get(), NightmareBlazeEntity.registerAttributes().create());
            ModVillagers.registerPOIs();
        });
        event.enqueueWork(() ->{
            AxeItem.BLOCK_STRIPPING_MAP = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.BLOCK_STRIPPING_MAP)
                    .put(ModBlocks.DREAM_LOG.get(), ModBlocks.STRIPPED_DREAM_LOG.get())
                    .put(ModBlocks.DREAM_WOOD.get(), ModBlocks.STRIPPED_DREAM_WOOD.get()).build();
        });

        ModStructures.setupStructures();
        ModdedStructures.setupStructures();
        ModBiomeGeneration.generateBiomes();
        DREAM = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(MOD_ID, "dream"));
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(ModBlocks.DREAM_BLASTER.get(),RenderType.getCutout());


            ScreenManager.registerFactory(ModContainers.DREAM_BLASTER_CONTAINER.get(),
                    DreamBlasterScreen::new);

            RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLUID.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.getTranslucent());

        });
        ModMessages.register();
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PENGUIN.get(), PenguinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.NIGHTMARE_BLAZE.get(), NightmareBlazeRenderer::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call


    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
