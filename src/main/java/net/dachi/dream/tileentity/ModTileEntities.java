package net.dachi.dream.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.dachi.dream.Dream;
import net.dachi.dream.block.ModBlocks;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Dream.MOD_ID);

    public static RegistryObject<TileEntityType<DreamBlasterTile>> DREAM_BLASTER_TILE =
            TILE_ENTITIES.register("dream_blaster_tile", () -> TileEntityType.Builder.create(
                    DreamBlasterTile::new, ModBlocks.DREAM_BLASTER.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}