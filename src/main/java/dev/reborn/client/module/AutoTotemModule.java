package dev.reborn.client.module;

import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Items;

/** Moves a totem from the player's inventory into the offhand when it is empty or not a totem. */
public final class AutoTotemModule extends ClientModule {
    public AutoTotemModule() {
        super("Auto Totem", "Keeps a Totem of Undying in your offhand when one is available.");
    }

    @Override
    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.gameMode == null || client.screen != null) return;
        var player = client.player;
        if (player.getOffhandItem().is(Items.TOTEM_OF_UNDYING)) return;

        for (int inventoryIndex = 0; inventoryIndex < player.getInventory().getContainerSize(); inventoryIndex++) {
            if (!player.getInventory().getItem(inventoryIndex).is(Items.TOTEM_OF_UNDYING)) continue;

            int menuSlot = inventoryIndex < 9 ? inventoryIndex + 36 : inventoryIndex;
            client.gameMode.handleInventoryMouseClick(
                    player.inventoryMenu.containerId, menuSlot, 40, ClickType.SWAP, player
            );
            return;
        }
    }
}
