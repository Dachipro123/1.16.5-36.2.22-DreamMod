package net.dachi.dream.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.dachi.dream.block.ModBlocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TeleportToDreamCommand {
    public TeleportToDreamCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("teleport").then(Commands.literal("dream").executes((command) -> {

            return returnHome(command.getSource());
        })));
    }

    private int returnHome(CommandSource source) throws CommandSyntaxException {
        placePortalDream((Supplier<NetworkEvent.Context>) source);

        return 1;

    }
    private void placePortalDream(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        ServerPlayerEntity player = context.getSender();
        World world = player.getEntityWorld();
        world.setBlockState(player.getPosition(), ModBlocks.DREAM_BLOCK.get().getDefaultState());
    }
}
