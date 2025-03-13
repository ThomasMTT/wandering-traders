package life.thoms.wandering_traders.menu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;

public class ViewOnlyChestMenu extends ChestMenu {

    public ViewOnlyChestMenu(MenuType<?> type, int containerId, Inventory playerInventory, Container container, int rows) {
        super(type, containerId, playerInventory, container, rows);
    }

    public static ViewOnlyChestMenu sixRows(int containerId, Inventory playerInventory, Container container) {
        return new ViewOnlyChestMenu(MenuType.GENERIC_9x6, containerId, playerInventory, container, 6);
    }

    @Override
    public void clicked(int slotId, int button, ClickType clickType, Player player) {

    }

}
