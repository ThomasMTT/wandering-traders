package life.thoms.wandering_traders.fabric.config;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.config.ModConfigs;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = WanderingTraders.MOD_ID)
public class ModConfigFabric implements ConfigData {

    public static ModConfigFabric CONFIG = null;

    public boolean ENABLE_ANIMAL_TRADER_SPAWN = true;
    public boolean ENABLE_BOOK_TRADER_SPAWN = true;
    public boolean ENABLE_DECORATION_TRADER_SPAWN = true;
    public boolean ENABLE_EXCLUSIVE_TRADER_SPAWN = true;
    public boolean ENABLE_FOREST_TRADER_SPAWN = true;
    public boolean ENABLE_POTION_TRADER_SPAWN = true;

    public boolean ENABLE_LOOT_RECOVERY = true;
    public boolean ENABLE_END_BELL_DROP = true;
    public boolean MESSAGES_TO_ACTIONBAR = true;
    public int TRADER_DESPAWN_TIME = 48000;

    public static void updateSharedConfigs() {
        ModConfigs.ENABLE_ANIMAL_TRADER_SPAWN = CONFIG.ENABLE_ANIMAL_TRADER_SPAWN;
        ModConfigs.ENABLE_BOOK_TRADER_SPAWN = CONFIG.ENABLE_BOOK_TRADER_SPAWN;
        ModConfigs.ENABLE_DECORATION_TRADER_SPAWN = CONFIG.ENABLE_DECORATION_TRADER_SPAWN;
        ModConfigs.ENABLE_EXCLUSIVE_TRADER_SPAWN = CONFIG.ENABLE_EXCLUSIVE_TRADER_SPAWN;
        ModConfigs.ENABLE_FOREST_TRADER_SPAWN = CONFIG.ENABLE_FOREST_TRADER_SPAWN;
        ModConfigs.ENABLE_POTION_TRADER_SPAWN = CONFIG.ENABLE_POTION_TRADER_SPAWN;
        ModConfigs.ENABLE_LOOT_RECOVERY = CONFIG.ENABLE_LOOT_RECOVERY;
        ModConfigs.ENABLE_END_BELL_DROP = CONFIG.ENABLE_END_BELL_DROP;
        ModConfigs.MESSAGES_TO_ACTIONBAR = CONFIG.MESSAGES_TO_ACTIONBAR;
        ModConfigs.TRADER_DESPAWN_TIME = CONFIG.TRADER_DESPAWN_TIME;
    }
}
