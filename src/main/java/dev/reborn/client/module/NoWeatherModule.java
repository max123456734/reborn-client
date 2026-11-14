package dev.reborn.client.module;

import net.minecraft.client.Minecraft;

public final class NoWeatherModule extends ClientModule {
    public NoWeatherModule() {
        super("Clear Weather", "Hides rain and snow locally.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.level != null) {
            client.level.effects().tickRain(client.level, 0);
        }
    }
}
