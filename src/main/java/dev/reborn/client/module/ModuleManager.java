package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ModuleManager {
    private static final List<ClientModule> MODULES = new ArrayList<>();

    private ModuleManager() {}

    public static void init() {
        if (!MODULES.isEmpty()) return;
        MODULES.add(new FullbrightModule());
        MODULES.add(new AutoSprintModule());
        MODULES.add(new AimAssistModule());
        MODULES.add(new TriggerBotModule());
        MODULES.add(new AutoTotemModule());
        MODULES.add(new CrystalBreakerModule());
        MODULES.add(new AutoJumpResetModule());
        MODULES.add(new AutoShieldBreakModule());
        MODULES.add(new AutoToolModule());
        MODULES.add(new BowAimAssistModule());
        MODULES.add(new AutoMaceModule());
        MODULES.add(new AutoWtapModule());
        MODULES.add(new FallPearlModule());
    }

    public static List<ClientModule> all() {
        return Collections.unmodifiableList(MODULES);
    }

    public static void tick(Minecraft client) {
        for (ClientModule module : MODULES) {
            if (module.isEnabled()) module.tick();
        }
    }
}
