package net.dachi.dream.poi;

import net.dachi.dream.Dream;
import net.dachi.dream.block.ModBlocks;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPOIs {

    public static final DeferredRegister<PointOfInterestType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, Dream.MOD_ID);

    public static final RegistryObject<PointOfInterestType> DREAM_PORTAL = POI.register("dream_portal", () -> new PointOfInterestType("dream_portal", PointOfInterestType.getAllStates(ModBlocks.DREAM_PORTAL_BLOCK.get()), 0, 1));
}


