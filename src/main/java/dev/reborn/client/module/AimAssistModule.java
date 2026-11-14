package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

/** Smoothly turns toward the nearest visible player in a short PvP range. */
public final class AimAssistModule extends ClientModule {
    private static final double RANGE = 4.5;
    private static final float TURN_SPEED = 0.22F;

    public AimAssistModule() {
        super("Aim Assist", "Gently tracks the nearest player within 4.5 blocks.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.level == null || client.screen != null) return;

        Player self = client.player;
        Player target = null;
        double bestDistance = RANGE;
        for (Player candidate : client.level.players()) {
            if (candidate == self || !candidate.isAlive() || candidate.isSpectator()) continue;
            double distance = self.distanceTo(candidate);
            if (distance < bestDistance) {
                bestDistance = distance;
                target = candidate;
            }
        }
        if (target == null) return;

        Vec3 delta = target.getEyePosition().subtract(self.getEyePosition());
        double horizontal = Math.sqrt(delta.x * delta.x + delta.z * delta.z);
        float wantedYaw = (float) (Math.toDegrees(Math.atan2(delta.z, delta.x)) - 90.0);
        float wantedPitch = (float) -Math.toDegrees(Math.atan2(delta.y, horizontal));
        self.setYRot(self.getYRot() + Mth.wrapDegrees(wantedYaw - self.getYRot()) * TURN_SPEED);
        self.setXRot(self.getXRot() + Mth.wrapDegrees(wantedPitch - self.getXRot()) * TURN_SPEED);
    }
}
