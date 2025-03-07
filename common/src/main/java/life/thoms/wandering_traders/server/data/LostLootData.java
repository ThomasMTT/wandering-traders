package life.thoms.wandering_traders.server.data;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.util.ServerDataUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LostLootData extends SavedData {

    public static final Map<UUID, List<ItemStack>> PLAYER_LOST_LOOT = new HashMap<>();
    public static LostLootData INSTANCE;

    public static void createServerState(MinecraftServer server) {
        INSTANCE = server.overworld().getDataStorage().computeIfAbsent(
                new Factory<>(LostLootData::create, LostLootData::load, DataFixTypes.LEVEL), WanderingTraders.MOD_ID);
        INSTANCE.setDirty();
    }

    public static LostLootData create() {
        return new LostLootData();
    }

    public static LostLootData load(CompoundTag tag, HolderLookup.Provider registryLookup) {
        ServerDataUtil.loadNbt(tag);
        return new LostLootData();
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("wandering-traders-player-loot", ServerDataUtil.saveNbt());
        return tag;
    }

}
