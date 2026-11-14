package dev.reborn.client.module;

import net.minecraft.client.Minecraft;

public final class AutoSprintModule extends ClientModule {
    public AutoSprintModule() {
        super("Auto Sprint", "Automatically holds sprint while moving forward.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player != null && client.options.keyUp.isDown()) {
            client.player.setSprinting(true);
        }
    }
}
