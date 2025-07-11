package life.thoms.wandering_traders.handler;

import life.thoms.wandering_traders.config.ModConfigs;
import life.thoms.wandering_traders.util.LootFilters;
import life.thoms.wandering_traders.util.LostLootUtil;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class EntityEventHandler {

    public static void onEntityLeaveLevel(Entity entity, Level level) {
        if (ModConfigs.ENABLE_LOOT_RECOVERY && isValuableItemEntity(entity)) {
            ItemEntity itemEntity = (ItemEntity) entity;
            Player player = (Player) itemEntity.getOwner();
            if (player == null) {
                ItemStack stack = itemEntity.getItem();
                DataComponentPatch dataComponentPatch = stack.getComponentsPatch();
                Optional<? extends CustomData> customData = dataComponentPatch.get(DataComponents.CUSTOM_DATA);

                if (customData.isPresent()) {
                    CompoundTag dataTag = customData.get().copyTag();
                    if (dataTag.contains("stack_owner")) {
                        player = level.getPlayerByUUID(dataTag.getUUID("stack_owner"));
                        stack.remove(DataComponents.CUSTOM_DATA);
                    }
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
                CustomData data = itemEntity.getItem().getComponents().get(DataComponents.CUSTOM_DATA);
                if (data != null) {
                    return data.contains("stack_owner");
                }
            }
        }
        return false;
    }

}
