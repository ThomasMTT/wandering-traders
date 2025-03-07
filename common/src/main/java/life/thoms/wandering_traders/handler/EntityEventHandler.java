package life.thoms.wandering_traders.handler;

import life.thoms.wandering_traders.server.data.LostLootData;
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
                UUID playerUUID = player.getUUID();
                if (LootFilters.isImportantLoot(itemEntity.getItem())) {
                    ItemStack itemStack = itemEntity.getItem();
                    List<ItemStack> playerLoot = LostLootData.PLAYER_LOST_LOOT.getOrDefault(playerUUID, new ArrayList<>());
                    playerLoot.add(itemStack);
                    LostLootData.PLAYER_LOST_LOOT.put(playerUUID, playerLoot);
                    if (level.getServer() != null) {
                        LostLootData.INSTANCE.setDirty();
                    }
                }
            }
        }
    }

}
