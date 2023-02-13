package net.dachi.dream.world.gen;

import net.dachi.dream.world.biome.ModBiomes;
import net.dachi.dream.world.structure.ModdedStructures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.dachi.dream.world.structure.ModStructures;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModStructureGeneration {
    public static void generateStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);


            List<Supplier<StructureFeature<?, ?>>> structures = event.getGeneration().getStructures();

            structures.add(() -> ModStructures.RUINED_DREAM_PORTAL.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            structures.add(() -> ModdedStructures.DREAM_FORTRESS.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

    }
}
