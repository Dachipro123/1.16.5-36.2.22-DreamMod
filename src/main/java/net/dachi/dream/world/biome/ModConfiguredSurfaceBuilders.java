package net.dachi.dream.world.biome;


import net.dachi.dream.block.ModBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.dachi.dream.Dream;

public class ModConfiguredSurfaceBuilders {
    public static ConfiguredSurfaceBuilder<?> DREAM_SURFACE = register("dream_surface",
            SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(
                    ModBlocks.DREAM_BLOCK.get().getDefaultState(),
                    ModBlocks.DREAM_BLOCK.get().getDefaultState(),
                    ModBlocks.DREAM_BLOCK.get().getDefaultState()
            )));

    private static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String name,
                                                                                           ConfiguredSurfaceBuilder<SC> csb) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,
                new ResourceLocation(Dream.MOD_ID, name), csb);
    }
}
