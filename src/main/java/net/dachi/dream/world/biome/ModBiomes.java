package net.dachi.dream.world.biome;

import net.dachi.dream.Dream;
import net.dachi.dream.entity.ModEntityTypes;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


import java.util.function.Supplier;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES
            = DeferredRegister.create(ForgeRegistries.BIOMES, Dream.MOD_ID);

    public static final RegistryObject<Biome> DREAM_BIOME = BIOMES.register("dream_biome",
            () -> makeDreamBiome(() -> ModConfiguredSurfaceBuilders.DREAM_SURFACE, 0.125f, 0.05f));

    private static Biome makeDreamBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder =
                (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(surfaceBuilder);



        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.LAKES, Features.LAKE_WATER);
        DefaultBiomeFeatures.withFrozenTopLayer(biomegenerationsettings$builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.SWAMP).depth(depth).scale(scale)
                .temperature(1.5F).downfall(0.9F).setEffects((new BiomeAmbience.Builder()).setWaterColor(0x00ffff).setWaterFogColor(0x00ffff)
                        .setFogColor(0x00ffff).withSkyColor(getSkyColorWithTemperatureModifier(0.8F)).withFoliageColor(0x00ffff).withGrassColor(0x00ffff)
                        .build())
                .withMobSpawnSettings(mobspawninfo$builder.build()).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }
}
