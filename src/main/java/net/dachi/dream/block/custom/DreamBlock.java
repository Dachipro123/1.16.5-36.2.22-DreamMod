package net.dachi.dream.block.custom;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;


public class DreamBlock extends Block {


    public DreamBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public void addInformation(ItemStack itemstack, IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(itemstack, world, list, flag);
            list.add(new TranslationTextComponent("tooltips.dream.dream_block"));
    }

    public static void giveBlindness(World worldIn, BlockPos pos, LivingEntity entityIn) {
        if (entityIn instanceof PlayerEntity){
            entityIn.addPotionEffect(new EffectInstance(Effects.BLINDNESS,20));
        } else {
            entityIn.sendMessage(new StringTextComponent(""),entityIn.getUniqueID());
        }

    }

}


