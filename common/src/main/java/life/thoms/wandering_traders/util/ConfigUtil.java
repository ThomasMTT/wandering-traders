package life.thoms.wandering_traders.util;

import life.thoms.wandering_traders.config.ModConfigs;
import life.thoms.wandering_traders.server.ModRegistryAccess;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

public class ConfigUtil {

    public static EntityType<? extends Mob> end_trader = ModRegistryAccess.ENTITY_ACCESS.get("end_trader");
    public static EntityType<? extends Mob> decoration_trader = ModRegistryAccess.ENTITY_ACCESS.get("decoration_trader");
    public static EntityType<? extends Mob> animal_trader = ModRegistryAccess.ENTITY_ACCESS.get("animal_trader");
    public static EntityType<? extends Mob> book_trader = ModRegistryAccess.ENTITY_ACCESS.get("book_trader");
    public static EntityType<? extends Mob> exclusive_trader = ModRegistryAccess.ENTITY_ACCESS.get("exclusive_trader");
    public static EntityType<? extends Mob> potion_trader= ModRegistryAccess.ENTITY_ACCESS.get("potion_trader");
    public static EntityType<? extends Mob> forest_trader= ModRegistryAccess.ENTITY_ACCESS.get("forest_trader");


    public static boolean spawnEnabled(EntityType<? extends Mob> traderType) {
        if (traderType.equals(end_trader)) {
            return ModConfigs.ENABLE_END_TRADER_SPAWN;
        } else if (traderType.equals(decoration_trader)) {
            return ModConfigs.ENABLE_DECORATION_TRADER_SPAWN;
        } else if (traderType.equals(animal_trader)) {
            return ModConfigs.ENABLE_ANIMAL_TRADER_SPAWN;
        } else if (traderType.equals(book_trader)) {
            return ModConfigs.ENABLE_BOOK_TRADER_SPAWN;
        } else if (traderType.equals(exclusive_trader)) {
            return ModConfigs.ENABLE_EXCLUSIVE_TRADER_SPAWN;
        } else if (traderType.equals(potion_trader)) {
            return ModConfigs.ENABLE_POTION_TRADER_SPAWN;
        } else if (traderType.equals(forest_trader)) {
            return ModConfigs.ENABLE_FOREST_TRADER_SPAWN;
        } else {
            throw new IllegalStateException("Unexpected trader type: " + traderType);
        }
    }


}
