package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

/** Aims toward the nearest visible player while the player is drawing a bow. */
public final class BowAimAssistModule extends ClientModule {
    public BowAimAssistModule() {
        super("Bow Aim Assist", "Smoothly aims toward a nearby player while drawing a bow.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.level == null || client.screen != null) return;
        var self = client.player;
        if (!self.isUsingItem() || !self.getUseItem().is(Items.BOW)) return;

        Player target = null;
        double best = 32.0;
        for (Player candidate : client.level.players()) {
            if (candidate == self || !candidate.isAlive() || candidate.isSpectator()) continue;
            double distance = self.distanceTo(candidate);
            if (distance < best) { best = distance; target = candidate; }
        }
        if (target == null) return;

        Vec3 delta = target.getEyePosition().subtract(self.getEyePosition());
        double horizontal = Math.sqrt(delta.x * delta.x + delta.z * delta.z);
        float yaw = (float)(Math.toDegrees(Math.atan2(delta.z, delta.x)) - 90.0);
        float pitch = (float)-Math.toDegrees(Math.atan2(delta.y, horizontal));
        self.setYRot(self.getYRot() + Mth.wrapDegrees(yaw - self.getYRot()) * 0.12F);
        self.setXRot(self.getXRot() + Mth.wrapDegrees(pitch - self.getXRot()) * 0.12F);
    }
}
