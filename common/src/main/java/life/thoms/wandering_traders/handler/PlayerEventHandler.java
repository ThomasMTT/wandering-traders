package life.thoms.wandering_traders.handler;

import life.thoms.wandering_traders.config.ModConfigs;
import life.thoms.wandering_traders.server.data.LostLootData;
import life.thoms.wandering_traders.util.LootFilters;
import life.thoms.wandering_traders.util.LostLootUtil;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class PlayerEventHandler {

    public static boolean onPlayerDeath(LivingEntity livingEntity, DamageSource damageSource, float ignored) {
        if (ModConfigs.ENABLE_LOOT_RECOVERY && livingEntity instanceof Player player && !player.isCreative()) {
            if (isPlayerFallingToVoid(player, damageSource)) {
                handlePlayerLootOnDeath(player);
            } else {
                // mark loot as from player so when it despawns its recoverable
                List<ItemStack> itemList = new ArrayList<>();
                itemList.addAll(player.getInventory().items);
                itemList.addAll(player.getInventory().armor);
                itemList.addAll(player.getInventory().offhand);
                for (ItemStack stack : itemList) {
                    if (LootFilters.isImportantLoot(stack)) {
                        CompoundTag stackDataTag = new CompoundTag();
                        stackDataTag.putUUID("stack_owner", player.getUUID());
                        TypedDataComponent<CustomData> comp =
                                new TypedDataComponent<>(DataComponents.CUSTOM_DATA, CustomData.of(stackDataTag));
                        DataComponentPatch patch = DataComponentPatch.builder().set(comp).build();
                        stack.applyComponents(patch);
                    }
                }
            }
        }
        return true;
    }

    private static boolean isPlayerFallingToVoid(Player player, DamageSource damageSource) {
        return damageSource.is(DamageTypes.FELL_OUT_OF_WORLD) || isFallingToVoid(player);
    }

    private static void handlePlayerLootOnDeath(Player player) {
        List<ItemStack> importantStacks = getImportantLootStacks(player);
        if (!importantStacks.isEmpty()) {
            List<ItemStack> playerLoot = LostLootUtil.getPlayerLoot(player);
            processImportantLoot(player, importantStacks, playerLoot);
            LostLootUtil.removeOldEntries(playerLoot);
            LostLootData.PLAYER_LOST_LOOT.put(player.getUUID(), playerLoot);

            if (player.level().getServer() != null) {
                LostLootData.INSTANCE.setDirty();
            }
        }
    }

    private static List<ItemStack> getImportantLootStacks(Player player) {
        List<ItemStack> stacks = new ArrayList<>();
        stacks.addAll(player.getInventory().items);
        stacks.addAll(player.getInventory().armor);
        stacks.addAll(player.getInventory().offhand);
        return stacks.stream().filter(LootFilters::isImportantLoot).toList();
    }

    private static void processImportantLoot(Player player, List<ItemStack> importantStacks, List<ItemStack> playerLoot) {
        for (ItemStack stack : importantStacks) {
            if (stack.isStackable()) {
                LostLootUtil.handleStackableLoot(player.getUUID(), stack);
            } else {
                playerLoot.add(stack);
            }
        }
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
