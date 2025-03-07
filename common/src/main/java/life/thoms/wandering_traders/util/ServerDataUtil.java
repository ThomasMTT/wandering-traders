package life.thoms.wandering_traders.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import life.thoms.wandering_traders.server.data.LostLootData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ServerDataUtil {

    public static void loadNbt(CompoundTag tag) {
        LostLootData.PLAYER_LOST_LOOT.clear();
        CompoundTag compound = tag.getCompound("wandering-traders-player-loot");

        for (String playerStringUUID : compound.getAllKeys()) {
            List<ItemStack> playerLostLoot = new ArrayList<>();
            UUID playerUUID = UUID.fromString(playerStringUUID);
            CompoundTag playerLootUUIDCompound = compound.getCompound(playerStringUUID);

            for (String lootUUID : playerLootUUIDCompound.getAllKeys()) {
                CompoundTag playerLootCompound = playerLootUUIDCompound.getCompound(lootUUID);
                String encodedStack = playerLootCompound.getAllKeys().stream().toList().getFirst();
                JsonElement encodedStackJson = JsonParser.parseString(encodedStack);
                DataResult<ItemStack> result = ItemStack.CODEC.parse(JsonOps.INSTANCE, encodedStackJson);
                Optional<ItemStack> optionalStack = result.result();
                optionalStack.ifPresent(playerLostLoot::add);
            }
            LostLootData.PLAYER_LOST_LOOT.put(playerUUID, playerLostLoot);
        }
    }

    public static CompoundTag saveNbt() {
        CompoundTag compound = new CompoundTag();
        for (UUID playerUUID : LostLootData.PLAYER_LOST_LOOT.keySet()) {
            CompoundTag playerLootUUIDCompound = new CompoundTag();
            List<ItemStack> playerLoot = LostLootData.PLAYER_LOST_LOOT.get(playerUUID);
            for (ItemStack stack : playerLoot) {
                CompoundTag playerLootCompound = new CompoundTag();
                DataResult<JsonElement> encodedStack = ItemStack.CODEC.encodeStart(JsonOps.INSTANCE, stack);
                playerLootCompound.putString(encodedStack.resultOrPartial().orElseThrow().toString(), "");
                playerLootUUIDCompound.put(UUID.randomUUID().toString(), playerLootCompound);
            }
            compound.put(playerUUID.toString(), playerLootUUIDCompound);
        }
        return compound;
    }

}
