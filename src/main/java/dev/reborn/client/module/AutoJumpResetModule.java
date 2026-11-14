package dev.reborn.client.module;

import net.minecraft.client.Minecraft;

/** Performs a grounded jump shortly after taking a hit to reduce knockback. */
public final class AutoJumpResetModule extends ClientModule {
    private int lastTriggeredTick = -1;

    public AutoJumpResetModule() {
        super("Auto Jump Reset", "Jumps shortly after taking damage when grounded.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.screen != null) return;
        var player = client.player;
        if (player.hurtTime >= 7 && player.onGround() && lastTriggeredTick != player.tickCount) {
            player.jumpFromGround();
            lastTriggeredTick = player.tickCount;
        }
    }
}
