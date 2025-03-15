package life.thoms.wandering_traders.util;

import life.thoms.wandering_traders.server.data.LostLootData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LostLootUtil {

    public static List<ItemStack> handleStackableLoot(UUID playerUUID, ItemStack stack) {
        List<ItemStack> playerLoot = LostLootUtil.getPlayerLoot(playerUUID);
        int maxStackSize = 16;

        // Try to merge with existing stacks
        for (ItemStack existingStack : playerLoot) {
            if (existingStack.getItem().equals(stack.getItem())) {
                int availableSpace = maxStackSize - existingStack.getCount();
                if (availableSpace > 0) {
                    int amountToAdd = Math.min(availableSpace, stack.getCount());
                    existingStack.setCount(existingStack.getCount() + amountToAdd);
                    stack.setCount(stack.getCount() - amountToAdd);

                    if (stack.getCount() <= 0) return playerLoot;
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

            stackCount = stackCount - amountToAdd;
        }
        return playerLoot;
    }

    //  max size = 54 (9 slots * 6 rows)
    public static void removeOldEntries(List<ItemStack> lostLoot) {
        while (lostLoot.size() > 54) {
            lostLoot.removeFirst();
        }
    }

    public static List<ItemStack> getPlayerLoot(UUID player) {
        return LostLootData.PLAYER_LOST_LOOT.getOrDefault(player, new ArrayList<>());
    }

    public static void putPlayerLoot(UUID player, List<ItemStack> stacksToPut) {
        removeOldEntries(stacksToPut);
        LostLootData.PLAYER_LOST_LOOT.put(player, stacksToPut);
    }

    public static void addPlayerLoot(UUID player, ItemStack stack) {
        List<ItemStack> playerLoot;
        if (stack.isStackable()) {
            playerLoot = handleStackableLoot(player, stack);
        } else {
            playerLoot = getPlayerLoot(player);
            playerLoot.add(stack);
        }
        removeOldEntries(playerLoot);
        LostLootData.PLAYER_LOST_LOOT.put(player, playerLoot);
    }

    public static void addPlayerLoot(UUID player, List<ItemStack> stacksToAdd) {
        List<ItemStack> playerLoot = getPlayerLoot(player);
        for (ItemStack stack : stacksToAdd) {
            if (stack.isStackable()) {
                playerLoot = handleStackableLoot(player, stack);
            } else {
                playerLoot.add(stack);
            }
        }
        removeOldEntries(playerLoot);
        LostLootData.PLAYER_LOST_LOOT.put(player, playerLoot);
    }

    public static void clearPlayerLoot(UUID player) {
        LostLootData.PLAYER_LOST_LOOT.put(player, new ArrayList<>());
    }

    public static List<ItemStack> getPlayerLoot(Player player) {
        return getPlayerLoot(player.getUUID());
    }

    public static void putPlayerLoot(Player player, List<ItemStack> stacksToPut) {
        putPlayerLoot(player.getUUID(), stacksToPut);
    }

    public static void addPlayerLoot(Player player, ItemStack stack) {
        addPlayerLoot(player.getUUID(), stack);
    }

    public static void addPlayerLoot(Player player, List<ItemStack> stacksToAdd) {
        addPlayerLoot(player.getUUID(), stacksToAdd);
    }

    public static void clearPlayerLoot(Player player) {
        clearPlayerLoot(player.getUUID());
    }

}
