package life.thoms.wandering_traders.fabric.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import life.thoms.wandering_traders.config.ModConfigs;

public class ConfigHandlerFabric {

    public static final ConfigClassHandler<ConfigHandlerFabric> CONFIG = ConfigClassHandler.createBuilder(ConfigHandlerFabric.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("wandering_traders.json"))
                    .build())
            .build();

    @SerialEntry(comment = "Enable trader spawns") public static boolean ENABLE_ANIMAL_TRADER_SPAWN = true;
    @SerialEntry public static boolean ENABLE_BOOK_TRADER_SPAWN = true;
    @SerialEntry public static boolean ENABLE_DECORATION_TRADER_SPAWN = true;
    @SerialEntry public static boolean ENABLE_EXCLUSIVE_TRADER_SPAWN = true;
    @SerialEntry public static boolean ENABLE_FOREST_TRADER_SPAWN = true;
    @SerialEntry public static boolean ENABLE_POTION_TRADER_SPAWN = true;

    @SerialEntry(comment = "Enable Loot recovery") public static boolean ENABLE_LOOT_RECOVERY = true;
    @SerialEntry(comment = "Enable bell drop (only if ENABLE_LOOT_RECOVERY is true)") public static boolean ENABLE_END_BELL_DROP = true;
    @SerialEntry(comment = "Display messages in actionbar (visible 2s above hotbar) or chat") public static boolean MESSAGES_TO_ACTIONBAR = true;
    @SerialEntry(comment = "Time it takes traders to despawn (24000 = 1 mc day = 20 minutes) (default 48000)") public static int TRADER_DESPAWN_TIME = 48000;

    public static void updateSharedConfigs() {
        ModConfigs.ENABLE_ANIMAL_TRADER_SPAWN = ENABLE_ANIMAL_TRADER_SPAWN;
        ModConfigs.ENABLE_BOOK_TRADER_SPAWN = ENABLE_BOOK_TRADER_SPAWN;
        ModConfigs.ENABLE_DECORATION_TRADER_SPAWN = ENABLE_DECORATION_TRADER_SPAWN;
        ModConfigs.ENABLE_EXCLUSIVE_TRADER_SPAWN = ENABLE_EXCLUSIVE_TRADER_SPAWN;
        ModConfigs.ENABLE_FOREST_TRADER_SPAWN = ENABLE_FOREST_TRADER_SPAWN;
        ModConfigs.ENABLE_POTION_TRADER_SPAWN = ENABLE_POTION_TRADER_SPAWN;
        ModConfigs.ENABLE_LOOT_RECOVERY = ENABLE_LOOT_RECOVERY;
        ModConfigs.ENABLE_END_BELL_DROP = ENABLE_END_BELL_DROP;
        ModConfigs.MESSAGES_TO_ACTIONBAR = MESSAGES_TO_ACTIONBAR;
        ModConfigs.TRADER_DESPAWN_TIME = TRADER_DESPAWN_TIME;
    }

}
