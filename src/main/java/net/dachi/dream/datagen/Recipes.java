package net.dachi.dream.datagen;

import net.dachi.dream.block.ModBlocks;
import net.dachi.dream.item.ModItems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider implements IConditionBuilder {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.DREAM_BLOCK.get())
                .key('C', ModItems.DREAM_INGOT.get())
                .patternLine("CCC")
                .patternLine("CCC")
                .patternLine("CCC")
                .addCriterion("has_dream_ingot", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().build()))
                .build(consumer);
    }
}
