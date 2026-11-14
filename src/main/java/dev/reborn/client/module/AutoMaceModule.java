package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.EntityHitResult;

/** Selects a mace when the crosshair is over a living player. */
public final class AutoMaceModule extends ClientModule {
    private int previousSlot = -1;

    public AutoMaceModule() {
        super("Auto Mace", "Selects a mace when aiming at a player and restores your slot otherwise.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.screen != null) return;
        var player = client.player;
        boolean target = client.hitResult instanceof EntityHitResult hit
                && hit.getEntity() instanceof Player other && other != player && other.isAlive();

        if (target) {
            int maceSlot = -1;
            for (int slot = 0; slot < 9; slot++) {
                if (player.getInventory().getItem(slot).is(Items.MACE)) { maceSlot = slot; break; }
            }
            if (maceSlot >= 0) {
                if (previousSlot < 0) previousSlot = player.getInventory().getSelectedSlot();
                player.getInventory().setSelectedSlot(maceSlot);
                return;
            }
        }
        restore(player);
    }

    private void restore(net.minecraft.world.entity.player.Player player) {
        if (previousSlot >= 0) player.getInventory().setSelectedSlot(previousSlot);
        previousSlot = -1;
    }

    @Override
    public void onDisable() {
        Minecraft client = Minecraft.getInstance();
        if (client.player != null) restore(client.player);
    }
}
