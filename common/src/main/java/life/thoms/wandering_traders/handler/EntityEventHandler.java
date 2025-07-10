package life.thoms.wandering_traders.handler;

import life.thoms.wandering_traders.config.ModConfigs;
import life.thoms.wandering_traders.util.LootFilters;
import life.thoms.wandering_traders.util.LostLootUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityEventHandler {

    public static void onEntityLeaveLevel(Entity entity, Level level) {
        if (ModConfigs.ENABLE_LOOT_RECOVERY && isValuableItemEntity(entity)) {
            ItemEntity itemEntity = (ItemEntity) entity;
            Player player = (Player) itemEntity.getOwner();
            LostLootUtil.addPlayerLoot(player, itemEntity.getItem());
        }
    }

    private static boolean isValuableItemEntity(Entity entity) {
        return entity instanceof ItemEntity itemEntity &&
                itemEntity.getOwner() instanceof Player &&
                LootFilters.isImportantLoot(itemEntity.getItem());
    }

}
