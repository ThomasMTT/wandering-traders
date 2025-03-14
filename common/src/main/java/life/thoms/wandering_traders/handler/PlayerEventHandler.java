package life.thoms.wandering_traders.handler;

import life.thoms.wandering_traders.server.data.LostLootData;
import life.thoms.wandering_traders.util.LootFilters;
import life.thoms.wandering_traders.util.LostLootUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class PlayerEventHandler {

    public static boolean onPlayerDeath(LivingEntity livingEntity, DamageSource damageSource, float ignored) {

        if (livingEntity instanceof Player player && !player.isCreative()) {
            if (damageSource.is(DamageTypes.FELL_OUT_OF_WORLD) || isFallingToVoid(player)) {

                List<ItemStack> stacks = new ArrayList<>();
                stacks.addAll(player.getInventory().items);
                stacks.addAll(player.getInventory().armor);
                stacks.addAll(player.getInventory().offhand);

                List<ItemStack> importantStacks = stacks.stream().filter(LootFilters::isImportantLoot).toList();
                if (!importantStacks.isEmpty()) {
                    List<ItemStack> playerLoot = LostLootUtil.getPlayerLoot(player);
                    for (ItemStack stack : importantStacks) {
                        if (stack.isStackable()) {
                            LostLootUtil.handleStackableLoot(player.getUUID(), stack);
                        } else {
                            playerLoot.add(stack);
                        }
                        if (player.level().getServer() != null) {
                            LostLootData.INSTANCE.setDirty();
                        }
                    }
                    LostLootUtil.removeOldEntries(playerLoot);
                    LostLootData.PLAYER_LOST_LOOT.put(player.getUUID(), playerLoot);
                }
            }
        }
        return true;
    }

    // Even if you don't die of falling to the void, if you have no blocks below, your items won't drop (this fixes it)
    private static boolean isFallingToVoid(Player player) {
        int playerHeight = (int) player.getY();
        while (playerHeight > player.level().getMinBuildHeight()) {
            if (!player.level().getBlockState(player.getOnPos().atY(playerHeight)).is(Blocks.AIR)) {
                return false;
            }
            playerHeight -= 1;
        }
        return true;
    }

}
