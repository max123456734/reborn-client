package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;

/** Briefly releases sprint when the player lands a hit, then resumes sprinting. */
public final class AutoWtapModule extends ClientModule {
    private int resumeSprintAt = -1;
    private int lastHurtTime;

    public AutoWtapModule() {
        super("Auto W-Tap", "Resets sprint briefly after a confirmed player hit.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.screen != null) return;
        var self = client.player;

        if (resumeSprintAt >= 0 && self.tickCount >= resumeSprintAt) {
            if (client.options.keyUp.isDown()) self.setSprinting(true);
            resumeSprintAt = -1;
        }

        if (client.hitResult instanceof EntityHitResult hit
                && hit.getEntity() instanceof Player target && target != self
                && target.hurtTime > lastHurtTime && target.hurtTime > 0) {
            self.setSprinting(false);
            resumeSprintAt = self.tickCount + 2;
        }
        if (client.hitResult instanceof EntityHitResult hit && hit.getEntity() instanceof Player target) {
            lastHurtTime = target.hurtTime;
        } else {
            lastHurtTime = 0;
        }
    }

    @Override
    public void onDisable() {
        resumeSprintAt = -1;
    }
}
