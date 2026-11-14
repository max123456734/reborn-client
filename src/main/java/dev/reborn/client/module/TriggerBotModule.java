package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;

/** Attacks a player only when the crosshair is already over them and the cooldown is ready. */
public final class TriggerBotModule extends ClientModule {
    public TriggerBotModule() {
        super("TriggerBot", "Attacks a player under your crosshair when your weapon is ready.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.level == null || client.gameMode == null || client.screen != null) return;
        if (!(client.hitResult instanceof EntityHitResult hit)) return;

        Entity target = hit.getEntity();
        if (!(target instanceof Player) || target == client.player || !target.isAlive()) return;
        if (client.player.getAttackStrengthScale(0.5F) < 1.0F) return;

        client.gameMode.attack(client.player, target);
        client.player.swing(InteractionHand.MAIN_HAND);
    }
}
