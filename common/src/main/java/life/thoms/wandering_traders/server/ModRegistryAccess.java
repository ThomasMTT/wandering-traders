package life.thoms.wandering_traders.server;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModRegistryAccess {

    public static final Map<String, Item> ITEM_ACCESS = new HashMap<>();
    public static final Map<String, EntityType<? extends WanderingTrader>> ENTITY_ACCESS = new HashMap<>();

    public static void addItem(String key, Item item) {
        ITEM_ACCESS.put(key, item);
    }

    public static void addEntityType(String key, EntityType<? extends WanderingTrader> entityType) {
        ENTITY_ACCESS.put(key, entityType);
    }

}
