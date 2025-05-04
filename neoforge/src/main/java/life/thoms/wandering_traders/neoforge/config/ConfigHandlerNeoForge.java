package life.thoms.wandering_traders.neoforge.config;

import life.thoms.wandering_traders.config.ModConfigs;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigHandlerNeoForge {

    public static final ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

    // Trader spawn settings
    public static final ModConfigSpec.BooleanValue ENABLE_ANIMAL_TRADER_SPAWN;
    public static final ModConfigSpec.BooleanValue ENABLE_BOOK_TRADER_SPAWN;
    public static final ModConfigSpec.BooleanValue ENABLE_DECORATION_TRADER_SPAWN;
    public static final ModConfigSpec.BooleanValue ENABLE_EXCLUSIVE_TRADER_SPAWN;
    public static final ModConfigSpec.BooleanValue ENABLE_FOREST_TRADER_SPAWN;
    public static final ModConfigSpec.BooleanValue ENABLE_POTION_TRADER_SPAWN;

    // Loot recovery settings
    public static final ModConfigSpec.BooleanValue ENABLE_LOOT_RECOVERY;

    // Bell drop settings
    public static final ModConfigSpec.BooleanValue ENABLE_END_BELL_DROP;

    // Actionbar message settings
    public static final ModConfigSpec.BooleanValue MESSAGES_TO_ACTIONBAR;

    // Trader despawn time
    public static final ModConfigSpec.IntValue TRADER_DESPAWN_TIME;

    static {
        builder.push("Trader Spawns");
        ENABLE_ANIMAL_TRADER_SPAWN = builder.comment("Enable trader spawns for animals").define("ENABLE_ANIMAL_TRADER_SPAWN", true);
        ENABLE_BOOK_TRADER_SPAWN = builder.comment("Enable trader spawns for books").define("ENABLE_BOOK_TRADER_SPAWN", true);
        ENABLE_DECORATION_TRADER_SPAWN = builder.comment("Enable trader spawns for decorations").define("ENABLE_DECORATION_TRADER_SPAWN", true);
        ENABLE_EXCLUSIVE_TRADER_SPAWN = builder.comment("Enable trader spawns for exclusive items").define("ENABLE_EXCLUSIVE_TRADER_SPAWN", true);
        ENABLE_FOREST_TRADER_SPAWN = builder.comment("Enable trader spawns for forest-related items").define("ENABLE_FOREST_TRADER_SPAWN", true);
        ENABLE_POTION_TRADER_SPAWN = builder.comment("Enable trader spawns for potions").define("ENABLE_POTION_TRADER_SPAWN", true);
        builder.pop();

        // Define loot recovery settings
        builder.push("Loot Recovery");
        ENABLE_LOOT_RECOVERY = builder.comment("Enable Loot recovery").define("ENABLE_LOOT_RECOVERY", true);

        ENABLE_END_BELL_DROP = builder.comment("Enable bell drop (only if ENABLE_LOOT_RECOVERY is true)").define("ENABLE_END_BELL_DROP", true);
        builder.pop();

        // Define message settings
        builder.push("Messages");
        MESSAGES_TO_ACTIONBAR = builder.comment("Display messages in actionbar (visible 2s above hotbar) or chat").define("MESSAGES_TO_ACTIONBAR", true);
        builder.pop();

        // Define trader despawn time settings
        builder.push("Trader Despawn");

        TRADER_DESPAWN_TIME =  builder.comment("Settings related to trader despawn time:",
                "Time it takes traders to despawn (24000 = 1 mc day = 20 minutes) (default 48000)")
                .defineInRange("TRADER_DESPAWN_TIME", 48000, 0, Integer.MAX_VALUE);

        builder.pop();
    }

    public static ModConfigSpec build() {
        return builder.build();
    }

    public static void updateSharedConfig() {
        ModConfigs.ENABLE_ANIMAL_TRADER_SPAWN = ENABLE_ANIMAL_TRADER_SPAWN.get();
        ModConfigs.ENABLE_BOOK_TRADER_SPAWN = ENABLE_BOOK_TRADER_SPAWN.get();
        ModConfigs.ENABLE_DECORATION_TRADER_SPAWN = ENABLE_DECORATION_TRADER_SPAWN.get();
        ModConfigs.ENABLE_EXCLUSIVE_TRADER_SPAWN = ENABLE_EXCLUSIVE_TRADER_SPAWN.get();
        ModConfigs.ENABLE_FOREST_TRADER_SPAWN = ENABLE_FOREST_TRADER_SPAWN.get();
        ModConfigs.ENABLE_POTION_TRADER_SPAWN = ENABLE_POTION_TRADER_SPAWN.get();
        ModConfigs.ENABLE_LOOT_RECOVERY = ENABLE_LOOT_RECOVERY.get();
        ModConfigs.ENABLE_END_BELL_DROP = ENABLE_END_BELL_DROP.get();
        ModConfigs.MESSAGES_TO_ACTIONBAR = MESSAGES_TO_ACTIONBAR.get();
        ModConfigs.TRADER_DESPAWN_TIME = TRADER_DESPAWN_TIME.get();
    }

}
