package net.dachi.dream.client;

import net.dachi.dream.Dream;
import net.dachi.dream.keybind.ModKeybinding;
import net.dachi.dream.networking.ModMessages;
import net.dachi.dream.networking.packet.GetDreamPointsC2SPacket;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Dream.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ModKeybinding.register(event);

    }
    @Mod.EventBusSubscriber(modid = Dream.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            if(ModKeybinding.dreamKey.isKeyDown()) {
                ModMessages.sendToServer(new GetDreamPointsC2SPacket());
            }
        }
    }
}
