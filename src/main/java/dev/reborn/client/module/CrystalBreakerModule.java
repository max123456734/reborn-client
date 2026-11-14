package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.phys.EntityHitResult;

/** Breaks an end crystal already selected by the player's crosshair. */
public final class CrystalBreakerModule extends ClientModule {
    public CrystalBreakerModule() {
        super("Crystal Breaker", "Breaks an end crystal under your crosshair when the attack cooldown is ready.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.gameMode == null || client.screen != null) return;
        if (!(client.hitResult instanceof EntityHitResult hit) || !(hit.getEntity() instanceof EndCrystal crystal)) return;
        if (client.player.getAttackStrengthScale(0.5F) < 1.0F) return;

        client.gameMode.attack(client.player, crystal);
        client.player.swing(InteractionHand.MAIN_HAND);
    }
}
