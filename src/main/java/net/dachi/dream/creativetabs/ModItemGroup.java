package net.dachi.dream.creativetabs;

import net.dachi.dream.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup DREAM_MOD_TAB = new ItemGroup("DreamModTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.DREAM_INGOT.get());
        }
    };
}
