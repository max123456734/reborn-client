package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;

/** Emergency pearl use while falling, only when a pearl is already held. */
public final class FallPearlModule extends ClientModule {
    public FallPearlModule() {
        super("Fall Pearl", "Uses a held ender pearl during a dangerous fall.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.gameMode == null || client.screen != null) return;
        var player = client.player;
        if (player.fallDistance < 8.0F || player.onGround()) return;

        InteractionHand hand = player.getMainHandItem().is(Items.ENDER_PEARL)
                ? InteractionHand.MAIN_HAND
                : player.getOffhandItem().is(Items.ENDER_PEARL) ? InteractionHand.OFF_HAND : null;
        if (hand != null) client.gameMode.useItem(player, hand);
    }
}
