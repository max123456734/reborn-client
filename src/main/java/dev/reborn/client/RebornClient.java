package dev.reborn.client;

import dev.reborn.client.gui.ClickGuiScreen;
import dev.reborn.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

public final class RebornClient implements ClientModInitializer {
    public static final String MOD_ID = "rebornclient";
    private static KeyMapping clickGuiKey;

    @Override
    public void onInitializeClient() {
        ModuleManager.init();
        clickGuiKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.rebornclient.clickgui",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (clickGuiKey.consumeClick()) {
                if (client.screen == null) {
                    client.setScreen(new ClickGuiScreen());
                } else if (client.screen instanceof ClickGuiScreen) {
                    client.setScreen(null);
                }
            }
            ModuleManager.tick(client);
        });
    }
}
