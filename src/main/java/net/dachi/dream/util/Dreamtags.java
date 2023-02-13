package net.dachi.dream.util;

import net.dachi.dream.Dream;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class Dreamtags {

    public static class Blocks {

        public static final Tags.IOptionalNamedTag<Block> PORTAL_FRAME_BLOCKS
                = createTag("portal_frame_blocks");


        private static Tags.IOptionalNamedTag<Block> createTag(String name){
            return BlockTags.createOptional(new ResourceLocation(Dream.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name){
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

    }

    public static class Items {

        private static Tags.IOptionalNamedTag<Item> createTag(String name){
            return ItemTags.createOptional(new ResourceLocation(Dream.MOD_ID, name));

        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name){
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }

    }

}
