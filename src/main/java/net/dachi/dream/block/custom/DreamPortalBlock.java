package net.dachi.dream.block.custom;


import net.dachi.dream.block.ModPortalSize;
import net.dachi.dream.world.dimension.portal.ModTeleporter;
import net.dachi.dream.block.ModBlocks;
import net.dachi.dream.util.Dreamtags;
import net.dachi.dream.world.dimension.ModDimensions;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;

import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;


import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PortalSize;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

public class DreamPortalBlock extends NetherPortalBlock {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public DreamPortalBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
    }


    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch((Direction.Axis)state.get(AXIS)) {
            case Z:
                return Z_AABB;
            case X:
            default:
                return X_AABB;
        }
    }
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        Direction.Axis direction$axis = facing.getAxis();
        Direction.Axis direction$axis1 = stateIn.get(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && !facingState.matchesBlock(this) && !(new PortalSize(worldIn, currentPos, direction$axis1)).validatePortal() ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!entityIn.isPassenger() && !entityIn.isBeingRidden() && entityIn.canChangeDimension()) {
            if (entityIn.hasPortalCooldown()) {
                entityIn.setPortalCooldown();
            } else {
                if (!entityIn.world.isRemote && !pos.equals(entityIn.entityBlockPosition)) {
                    entityIn.entityBlockPosition = pos.toImmutable();
                }
                World entityWorld = entityIn.world;
                if(entityWorld != null) {
                    MinecraftServer minecraftserver = entityWorld.getServer();
                    RegistryKey<World> destination = entityIn.world.getDimensionKey() == ModDimensions.DREAM ? World.OVERWORLD : ModDimensions.DREAM;
                    if(minecraftserver != null) {
                        ServerWorld destinationWorld = minecraftserver.getWorld(destination);
                        if(destinationWorld != null && minecraftserver.getAllowNether() && !entityIn.isPassenger()) {
                            entityIn.world.getProfiler().startSection("dream_portal");
                            entityIn.setPortalCooldown();
                            entityIn.changeDimension(destinationWorld, new ModTeleporter(destinationWorld));
                            entityIn.world.getProfiler().endSection();
                        }
                    }
                }
            }
        }

    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for(int i = 0; i < 4; ++i) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble();
            double d2 = (double)pos.getZ() + rand.nextDouble();
            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;
            if (!worldIn.getBlockState(pos.west()).matchesBlock(this) && !worldIn.getBlockState(pos.east()).matchesBlock(this)) {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
            } else {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
            }

            worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
        }

    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        switch(rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch((Direction.Axis)state.get(AXIS)) {
                    case Z:
                        return state.with(AXIS, Direction.Axis.X);
                    case X:
                        return state.with(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }


}
