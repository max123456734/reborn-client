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
        MODULES.add(new NoWeatherModule());
        MODULES.add(new ModuleStub("Aim Assist", "Combat module scaffold; implementation is not active yet."));
        MODULES.add(new ModuleStub("TriggerBot", "Combat module scaffold; implementation is not active yet."));
        MODULES.add(new ModuleStub("Auto Totem", "Inventory module scaffold; implementation is not active yet."));
        MODULES.add(new ModuleStub("Auto Crystal", "Combat module scaffold; implementation is not active yet."));
        MODULES.add(new ModuleStub("Auto Anchor", "Combat module scaffold; implementation is not active yet."));
        MODULES.add(new ModuleStub("Player ESP", "Visual module scaffold; implementation is not active yet."));
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
