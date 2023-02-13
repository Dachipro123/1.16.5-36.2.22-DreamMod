package net.dachi.dream.block.custom;

import net.dachi.dream.container.DreamBlasterContainer;
import net.dachi.dream.tileentity.DreamBlasterTile;
import net.dachi.dream.tileentity.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class DreamBlasterBlock extends HorizontalBlock  {
    public DreamBlasterBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(13, 0, 0, 16, 3, 3),
            Block.makeCuboidShape(13, 0, 13, 16, 3, 16),
            Block.makeCuboidShape(0, 0, 13, 3, 3, 16),
            Block.makeCuboidShape(0, 0, 0, 3, 3, 3),
            Block.makeCuboidShape(3, 2, 4, 13, 6, 5),
            Block.makeCuboidShape(2, 0, 1, 14, 2, 14),
            Block.makeCuboidShape(3, 0, 15, 13, 2, 16),
            Block.makeCuboidShape(3, 2, 5, 13, 14, 14),
            Block.makeCuboidShape(3, 0, 14, 13, 7, 15),
            Block.makeCuboidShape(4, 13, 7, 12, 15, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(13, 0, 13, 16, 3, 16),
            Block.makeCuboidShape(0, 0, 13, 3, 3, 16),
            Block.makeCuboidShape(0, 0, 0, 3, 3, 3),
            Block.makeCuboidShape(13, 0, 0, 16, 3, 3),
            Block.makeCuboidShape(11, 2, 3, 12, 6, 13),
            Block.makeCuboidShape(2, 0, 2, 15, 2, 14),
            Block.makeCuboidShape(0, 0, 3, 1, 2, 13),
            Block.makeCuboidShape(2, 2, 3, 11, 14, 13),
            Block.makeCuboidShape(1, 0, 3, 2, 7, 13),
            Block.makeCuboidShape(3, 13, 4, 9, 15, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(0, 0, 13, 3, 3, 16),
            Block.makeCuboidShape(0, 0, 0, 3, 3, 3),
            Block.makeCuboidShape(13, 0, 0, 16, 3, 3),
            Block.makeCuboidShape(13, 0, 13, 16, 3, 16),
            Block.makeCuboidShape(3, 2, 11, 13, 6, 12),
            Block.makeCuboidShape(2, 0, 2, 14, 2, 15),
            Block.makeCuboidShape(3, 0, 0, 13, 2, 1),
            Block.makeCuboidShape(3, 2, 2, 13, 14, 11),
            Block.makeCuboidShape(3, 0, 1, 13, 7, 2),
            Block.makeCuboidShape(4, 13, 3, 12, 15, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 3, 3, 3),
            Block.makeCuboidShape(13, 0, 0, 16, 3, 3),
            Block.makeCuboidShape(13, 0, 13, 16, 3, 16),
            Block.makeCuboidShape(0, 0, 13, 3, 3, 16),
            Block.makeCuboidShape(4, 2, 3, 5, 6, 13),
            Block.makeCuboidShape(1, 0, 2, 14, 2, 14),
            Block.makeCuboidShape(15, 0, 3, 16, 2, 13),
            Block.makeCuboidShape(5, 2, 3, 14, 14, 13),
            Block.makeCuboidShape(14, 0, 3, 15, 7, 13),
            Block.makeCuboidShape(7, 13, 4, 13, 15, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(HORIZONTAL_FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if(!player.isCrouching()) {
                if(tileEntity instanceof DreamBlasterTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);

                    NetworkHooks.openGui(((ServerPlayerEntity)player), containerProvider, tileEntity.getPos());
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            } else {
                if(tileEntity instanceof DreamBlasterTile) {
                    if(worldIn.isThundering()) {
                        EntityType.LIGHTNING_BOLT.spawn(((ServerWorld) worldIn), null, player,
                                pos, SpawnReason.TRIGGERED, true, true);

                        ((DreamBlasterTile)tileEntity).lightningHasStruck();
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.dream.dream_blaster");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new DreamBlasterContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.DREAM_BLASTER_TILE.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    
}
