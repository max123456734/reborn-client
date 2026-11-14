package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;

/** Selects a tiered tool from the hotbar that is effective against the targeted block. */
public final class AutoToolModule extends ClientModule {
    public AutoToolModule() {
        super("Auto Tool", "Selects the most effective tiered tool for the block under your crosshair.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.level == null || client.hitResult == null || client.screen != null) return;
        if (client.hitResult.getType() != net.minecraft.world.phys.HitResult.Type.BLOCK) return;

        var blockHit = (net.minecraft.world.phys.BlockHitResult) client.hitResult;
        var state = client.level.getBlockState(blockHit.getBlockPos());
        int bestSlot = -1;
        float bestSpeed = 1.0F;
        for (int slot = 0; slot < 9; slot++) {
            ItemStack stack = client.player.getInventory().getItem(slot);
            if (!(stack.getItem() instanceof TieredItem)) continue;
            float speed = stack.getDestroySpeed(state);
            if (speed > bestSpeed) {
                bestSpeed = speed;
                bestSlot = slot;
            }
        }
        if (bestSlot >= 0) client.player.getInventory().setSelectedSlot(bestSlot);
    }
}
