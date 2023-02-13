package net.dachi.dream.block;


import net.dachi.dream.Dream;
import net.dachi.dream.block.custom.DreamBlasterBlock;
import net.dachi.dream.block.custom.DreamBlock;
import net.dachi.dream.block.custom.DreamPortalBlock;
import net.dachi.dream.block.custom.NightmareBlock;
import net.dachi.dream.creativetabs.ModItemGroup;
import net.dachi.dream.item.ModItems;
import net.dachi.dream.world.dimension.portal.ModTeleporter;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, Dream.MOD_ID);



    public static final RegistryObject<Block> DREAM_ORE = registerBlock("dream_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(5f)));


    public static final RegistryObject<Block> DREAM_BLOCK = registerBlock("dream_block",
            () -> new DreamBlock(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(8f)));

    public static final RegistryObject<Block> NIGHTMARE_BLOCK = registerBlock("nightmare_block",
            () -> new NightmareBlock(AbstractBlock.Properties.create(Material.EARTH)
                    .harvestLevel(2).harvestTool(ToolType.SHOVEL).setRequiresTool().hardnessAndResistance(8f)));

    public static final RegistryObject<Block> DREAM_BLASTER = registerBlock("dream_blaster",
            () -> new DreamBlasterBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(8f).notSolid()));

    public static final RegistryObject<Block> DREAM_BRICKS = registerBlock("dream_bricks",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(6f)));

    //public static final RegistryObject<Block> LAZARE_AIT_BLOCK = registerBlock("lazare_ait_block",
    //        () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
    //                .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(6f)));
//
    //public static final RegistryObject<Block> TEMO_GRANDPAS_DIARY = registerBlock("temo_grandpas_diary",
    //        () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
    //                .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(6f)));

    public static final RegistryObject<Block> DREAM_PORTAL_BLOCK = registerBlockWithoutBlockItem("dream_portal_block",
            () -> new DreamPortalBlock(AbstractBlock.Properties.from(Blocks.NETHER_PORTAL)
                    .setLightLevel((state) -> 10).noDrops().notSolid().hardnessAndResistance(-6f)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.DREAM_MOD_TAB)));
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }



}
