package life.thoms.wandering_traders.config;

public class ModConfigs {

    // Enable trader spawns
    public static boolean ENABLE_ANIMAL_TRADER_SPAWN = true;
    public static boolean ENABLE_BOOK_TRADER_SPAWN = true;
    public static boolean ENABLE_DECORATION_TRADER_SPAWN = true;
    public static boolean ENABLE_EXCLUSIVE_TRADER_SPAWN = true;
    public static boolean ENABLE_FOREST_TRADER_SPAWN = true;
    public static boolean ENABLE_POTION_TRADER_SPAWN = true;

    // Enable Loot recovery
    public static boolean ENABLE_LOOT_RECOVERY = true;

    // Enable bell drop (only if ENABLE_LOOT_RECOVERY is true)
    public static boolean ENABLE_END_BELL_DROP = true;

    // Display messages in actionbar (visible 2s above hotbar) or chat
    public static boolean MESSAGES_TO_ACTIONBAR = true;

    // Time it takes traders to despawn (24000 = 1 mc day = 20 minutes) (default 48000)
    public static int TRADER_DESPAWN_TIME = 48000;

}
