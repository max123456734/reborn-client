package dev.reborn.client.gui;

import dev.reborn.client.module.ClientModule;
import dev.reborn.client.module.ModuleManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public final class ClickGuiScreen extends Screen {
    private static final int PANEL_WIDTH = 230;
    private static final int ROW_HEIGHT = 24;

    public ClickGuiScreen() {
        super(Component.literal("Reborn Client"));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(graphics, mouseX, mouseY, delta);
        int left = this.width / 2 - PANEL_WIDTH / 2;
        int top = 35;
        int height = 34 + ModuleManager.all().size() * ROW_HEIGHT;
        graphics.fill(left, top, left + PANEL_WIDTH, top + height, 0xE9101117);
        graphics.fill(left, top, left + PANEL_WIDTH, top + 3, 0xFF48D6A0);
        graphics.drawString(this.font, "REBORN CLIENT", left + 12, top + 12, 0xFFFFFFFF, false);
        graphics.drawString(this.font, "Right Shift to close  |  Click to toggle", left + 12, top + 24, 0xFF9AA4B2, false);

        int y = top + 42;
        for (ClientModule module : ModuleManager.all()) {
            boolean hovered = mouseX >= left + 8 && mouseX <= left + PANEL_WIDTH - 8
                    && mouseY >= y - 4 && mouseY < y + ROW_HEIGHT - 4;
            int bg = module.isEnabled() ? 0xFF174D40 : (hovered ? 0xFF292D39 : 0xFF1B1E27);
            graphics.fill(left + 8, y - 4, left + PANEL_WIDTH - 8, y + ROW_HEIGHT - 5, bg);
            graphics.drawString(this.font, module.name(), left + 16, y + 3, 0xFFFFFFFF, false);
            graphics.drawString(this.font, module.isEnabled() ? "ON" : "OFF",
                    left + PANEL_WIDTH - 36, y + 3, module.isEnabled() ? 0xFF63E6BE : 0xFF9AA4B2, false);
            y += ROW_HEIGHT;
        }
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            int left = this.width / 2 - PANEL_WIDTH / 2;
            int y = 35 + 42;
            for (ClientModule module : ModuleManager.all()) {
                if (mouseX >= left + 8 && mouseX <= left + PANEL_WIDTH - 8
                        && mouseY >= y - 4 && mouseY < y + ROW_HEIGHT - 4) {
                    if (!(module instanceof dev.reborn.client.module.ModuleStub)) module.toggle();
                    return true;
                }
                y += ROW_HEIGHT;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            this.minecraft.setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
