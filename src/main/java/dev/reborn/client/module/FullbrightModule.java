package dev.reborn.client.module;

import net.minecraft.client.Minecraft;

public final class FullbrightModule extends ClientModule {
    private double previousGamma;

    public FullbrightModule() {
        super("Fullbright", "Raises gamma while enabled.");
    }

    @Override
    public void onEnable() {
        Minecraft client = Minecraft.getInstance();
        previousGamma = client.options.gamma().get();
        client.options.gamma().set(16.0);
    }

    @Override
    public void tick() {
        Minecraft.getInstance().options.gamma().set(16.0);
    }

    @Override
    public void onDisable() {
        Minecraft.getInstance().options.gamma().set(previousGamma);
    }
}
