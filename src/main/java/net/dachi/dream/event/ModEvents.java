package net.dachi.dream.event;

import net.dachi.dream.Dream;
import net.dachi.dream.commands.ReturnHomeCommand;
import net.dachi.dream.commands.SetHomeCommand;
import net.dachi.dream.commands.TeleportToDreamCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = Dream.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());
        new TeleportToDreamCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if(!event.getOriginal().getEntityWorld().isRemote()) {
            event.getPlayer().getPersistentData().putIntArray(Dream.MOD_ID + "homepos",
                    event.getOriginal().getPersistentData().getIntArray(Dream.MOD_ID + "homepos"));
        }
    }
    
}
