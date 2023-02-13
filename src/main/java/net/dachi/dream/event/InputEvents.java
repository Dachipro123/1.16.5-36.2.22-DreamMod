package net.dachi.dream.event;

import net.dachi.dream.Dream;
import net.dachi.dream.keybind.ModKeybinding;
import net.dachi.dream.networking.ModMessages;
import net.dachi.dream.networking.packet.GetDreamPointsC2SPacket;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.dachi.dream.keybind.ModKeybinding.serverPlayerEntity;

@Mod.EventBusSubscriber(modid = Dream.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {


    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world == null) return;
        onInput(mc, event.getKey(), event.getAction());
    }

    @SubscribeEvent
    public static void onMouseClick(InputEvent.MouseInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world == null) return;
        onInput(mc, event.getButton(), event.getAction());
    }

    private static void onInput(Minecraft mc, int key, int action) {
        if (mc.currentScreen == null && ModKeybinding.dreamKey.isPressed()) {
            ModMessages.sendToServer(new GetDreamPointsC2SPacket());
        }
    }
}
