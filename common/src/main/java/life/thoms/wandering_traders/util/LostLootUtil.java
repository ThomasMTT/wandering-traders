package life.thoms.wandering_traders.util;

import life.thoms.wandering_traders.server.data.LostLootData;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LostLootUtil {

    public static void handleStackableLoot(UUID playerUUID, ItemStack stack) {
        if (stack.getCount() <= 0) return;
        int maxStackSize = 16;

        List<ItemStack> playerLoot = LostLootData.PLAYER_LOST_LOOT.getOrDefault(playerUUID, new ArrayList<>());

        // Try to merge with existing stacks
        for (ItemStack existingStack : playerLoot) {
            if (existingStack.getItem().equals(stack.getItem())) {
                int availableSpace = maxStackSize - existingStack.getCount();
                if (availableSpace > 0) {
                    int amountToAdd = Math.min(availableSpace, stack.getCount());
                    existingStack.setCount(existingStack.getCount() + amountToAdd);
                    stack.setCount(stack.getCount() - amountToAdd);

                    if (stack.getCount() <= 0) return;
                }
            }
        }

        // Add new stacks if there are remaining items
        int stackCount = stack.getCount();
        while (stackCount > 0) {
            int amountToAdd = Math.min(stack.getCount(), maxStackSize);
            ItemStack newStack = stack.copy();
            newStack.setCount(amountToAdd);
            playerLoot.add(newStack);
            LostLootData.PLAYER_LOST_LOOT.put(playerUUID, playerLoot);
            stackCount = stackCount - amountToAdd;
        }
    }

}
