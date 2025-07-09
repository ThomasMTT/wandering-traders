package life.thoms.wandering_traders.server.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.util.LostLootUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LostLootData extends SavedData {

    public static final Map<UUID, List<ItemStack>> PLAYER_LOST_LOOT = new HashMap<>();
    public static SavedData INSTANCE;

    public static void createServerState(MinecraftServer server) {
        INSTANCE = server.overworld().getDataStorage().computeIfAbsent(
                new Factory<>(LostLootData::create, LostLootData::load, DataFixTypes.LEVEL), WanderingTraders.MOD_ID + "-lostloot");
        INSTANCE.setDirty();
    }

    public static LostLootData create() {
        return new LostLootData();
    }

    public static LostLootData load(CompoundTag tag, HolderLookup.Provider registryLookup) {
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
            LostLootUtil.putPlayerLoot(playerUUID, playerLostLoot);
        }
        return new LostLootData();
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        CompoundTag compound = new CompoundTag();
        for (UUID playerUUID : LostLootData.PLAYER_LOST_LOOT.keySet()) {
            CompoundTag playerLootUUIDCompound = new CompoundTag();
            List<ItemStack> playerLoot = LostLootUtil.getPlayerLoot(playerUUID);

            for (ItemStack stack : playerLoot) {
                CompoundTag playerLootCompound = new CompoundTag();
                DataResult<JsonElement> encodedStack = ItemStack.CODEC.encodeStart(JsonOps.INSTANCE, stack);
                playerLootCompound.putString(encodedStack.resultOrPartial().orElseThrow().toString(), "");
                playerLootUUIDCompound.put(UUID.randomUUID().toString(), playerLootCompound);
            }
            compound.put(playerUUID.toString(), playerLootUUIDCompound);
        }
        tag.put("wandering-traders-player-loot", compound);
        return tag;
    }

}
