package net.dachi.dream.networking.packet;

import io.netty.buffer.ByteBuf;
import net.dachi.dream.Dream;
import net.dachi.dream.block.ModBlocks;
import net.dachi.dream.block.custom.DreamBlock;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.resources.AsyncReloader;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;
import java.util.function.Supplier;



public class GetDreamPointsC2SPacket {
    public PlayerEvent playerEvent;
    private static final Logger LOGGER = LogManager.getLogger();
    public static DreamBlock dreamBlock;

    public GetDreamPointsC2SPacket() {

    }

    public GetDreamPointsC2SPacket(ByteBuf buf) {

    }

    public void toBytes(ByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {

            NetworkEvent.Context context = supplier.get();
            context.enqueueWork(() -> {
                ServerPlayerEntity player = context.getSender();
                if (player.hasPortalCooldown()){
                    return ;
                } else {
                    player.setPortalCooldown();
                    //player.addItemStackToInventory(new ItemStack(Items.FLINT_AND_STEEL));
                    World world = player.getEntityWorld();
                    world.setBlockState(player.getPosition().down(), ModBlocks.DREAM_BLOCK.get().getDefaultState());
                    Minecraft.getInstance().player.sendMessage((ITextComponent)new StringTextComponent("You got Dream points click" + "more times to pass "),null);
                }
            });
            context.setPacketHandled(true);


        return true;
    }


    //playerEvent.getPlayer().fac()
}
