package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.phys.EntityHitResult;

/** Selects an axe and attacks a blocking player already under the crosshair. */
public final class AutoShieldBreakModule extends ClientModule {
    private int previousSlot = -1;
    private int restoreAt = -1;

    public AutoShieldBreakModule() {
        super("Auto Shield Break", "Uses a hotbar axe against a blocking player under your crosshair.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.gameMode == null || client.screen != null) return;
        var player = client.player;

        if (restoreAt >= 0 && player.tickCount >= restoreAt) {
            if (previousSlot >= 0) player.getInventory().setSelectedSlot(previousSlot);
            previousSlot = -1;
            restoreAt = -1;
        }

        if (!(client.hitResult instanceof EntityHitResult hit) || !(hit.getEntity() instanceof Player target)) return;
        if (target == player || !target.isAlive() || !target.isBlocking()) return;
        if (player.getAttackStrengthScale(0.5F) < 1.0F) return;

        int axeSlot = -1;
        for (int slot = 0; slot < 9; slot++) {
            if (player.getInventory().getItem(slot).getItem() instanceof AxeItem) {
                axeSlot = slot;
                break;
            }
        }
        if (axeSlot < 0) return;

        if (previousSlot < 0) previousSlot = player.getInventory().getSelectedSlot();
        player.getInventory().setSelectedSlot(axeSlot);
        client.gameMode.attack(player, target);
        player.swing(InteractionHand.MAIN_HAND);
        restoreAt = player.tickCount + 2;
    }

    @Override
    public void onDisable() {
        Minecraft client = Minecraft.getInstance();
        if (client.player != null && previousSlot >= 0) {
            client.player.getInventory().setSelectedSlot(previousSlot);
        }
        previousSlot = -1;
        restoreAt = -1;
    }
}
