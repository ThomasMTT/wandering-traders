package life.thoms.wandering_traders.handler;

import life.thoms.wandering_traders.config.ModConfigs;
import life.thoms.wandering_traders.util.LootFilters;
import life.thoms.wandering_traders.util.LostLootUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class EntityEventHandler {

    public static void onEntityLeaveLevel(Entity entity, Level level) {
        if (ModConfigs.ENABLE_LOOT_RECOVERY && isValuableItemEntity(entity)) {
            ItemEntity itemEntity = (ItemEntity) entity;
            Player player = (Player) itemEntity.getOwner();
            if (player == null) {
                ItemStack stack = itemEntity.getItem();
                CompoundTag itemTag = itemEntity.getItem().getTag();
                if (itemTag != null) {
                    UUID playerUUID = itemTag.getUUID("stack_owner");
                    player = level.getPlayerByUUID(playerUUID);
                    itemTag.remove("stack_owner");
                }

            }
            if (player != null) {
                LostLootUtil.addPlayerLoot(player, itemEntity.getItem());
            }
        }
    }

    private static boolean isValuableItemEntity(Entity entity) {
        if (entity instanceof ItemEntity itemEntity && LootFilters.isImportantLoot(itemEntity.getItem())) {
            if (itemEntity.getOwner() instanceof Player) {
                return true;
            } else {
                CompoundTag itemTag = itemEntity.getItem().getTag();
                if (itemTag != null) {
                    return itemTag.contains("stack_owner");
                }
            }
        }
        return false;
    }

}
