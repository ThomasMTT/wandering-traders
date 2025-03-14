package life.thoms.wandering_traders.handler;

import life.thoms.wandering_traders.server.data.LostLootData;
import life.thoms.wandering_traders.util.LostLootUtil;
import life.thoms.wandering_traders.util.LootFilters;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityEventHandler {

    public static void onEntityLeaveLevel(Entity entity, Level level) {
        if (entity instanceof ItemEntity itemEntity) {
            if (itemEntity.getOwner() instanceof Player player) {
                if (LootFilters.isImportantLoot(itemEntity.getItem())) {
                    ItemStack stack = itemEntity.getItem();
                    LostLootUtil.addPlayerLoot(player, stack);
                }
            }
        }
    }

}
