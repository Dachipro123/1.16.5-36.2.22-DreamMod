package net.dachi.dream.block.custom;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.List;


public class NightmareBlock extends Block {
    public NightmareBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        DreamBlock.giveBlindness(worldIn,pos, (LivingEntity) entityIn);
        super.onEntityWalk(worldIn, pos, entityIn);
    }
    @Override
    public void addInformation(ItemStack itemstack, IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(itemstack, world, list, flag);
        list.add(new TranslationTextComponent("tooltips.dream.nightmare_block"));
    }

}
