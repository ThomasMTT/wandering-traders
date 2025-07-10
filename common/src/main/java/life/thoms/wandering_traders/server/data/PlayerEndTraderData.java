package life.thoms.wandering_traders.server.data;

import life.thoms.wandering_traders.WanderingTraders;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerEndTraderData extends SavedData {
    public static final Map<UUID, UUID> PLAYER_END_TRADER_MAP = new HashMap<>();

    public static SavedData INSTANCE;

    public static void createServerState(MinecraftServer server) {
        INSTANCE = server.overworld().getDataStorage().computeIfAbsent(
                PlayerEndTraderData::load, PlayerEndTraderData::create, WanderingTraders.MOD_ID + "endtraderdata");
        INSTANCE.setDirty();
    }

    public static PlayerEndTraderData create() {
        return new PlayerEndTraderData();
    } // TODO test

    public static PlayerEndTraderData load(CompoundTag tag) {
        PlayerEndTraderData.PLAYER_END_TRADER_MAP.clear();
        CompoundTag compoundTag = tag.getCompound("wandering-traders.player-linked-end-trader");
        for (String playerUUIDStr : compoundTag.getAllKeys()) {
            UUID playerUUID = UUID.fromString(playerUUIDStr);
            UUID traderUUID = compoundTag.getUUID(playerUUIDStr);
            PLAYER_END_TRADER_MAP.put(playerUUID, traderUUID);
        }

        return new PlayerEndTraderData();
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        CompoundTag compoundTag = new CompoundTag();
        for (UUID playerUUID : PLAYER_END_TRADER_MAP.keySet()) {
            UUID traderUUID = PLAYER_END_TRADER_MAP.get(playerUUID);
            compoundTag.putUUID(playerUUID.toString(), traderUUID);
        }
        tag.put("wandering-traders.player-linked-end-trader", compoundTag);
        return tag;
    }
}
