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
