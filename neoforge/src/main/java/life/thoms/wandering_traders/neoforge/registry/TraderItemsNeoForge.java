package life.thoms.wandering_traders.neoforge.registry;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.item.*;
import life.thoms.wandering_traders.server.ModRegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TraderItemsNeoForge {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            BuiltInRegistries.ITEM,
            WanderingTraders.MOD_ID
    );

    // Spawn Eggs
    public static final DeferredHolder<Item, DeferredSpawnEggItem> END_TRADER_SPAWN_EGG = ITEMS.register(
            "end_trader_spawn_egg",
            () -> new DeferredSpawnEggItem(TraderEntitiesNeoForge.END_TRADER,
                    0x5e0da5, 0xcea512, new Item.Properties())
    );

    public static final DeferredHolder<Item, DeferredSpawnEggItem> GAMBLING_TRADER_SPAWN_EGG = ITEMS.register(
            "gambling_trader_spawn_egg",
            () -> new DeferredSpawnEggItem(TraderEntitiesNeoForge.GAMBLING_TRADER,
                    0xeaeef5, 0xcea512, new Item.Properties())
    );

    // Loot Boxes
    public static final DeferredHolder<Item, Item> ARMOR_LOOT_BOX = ITEMS.register(
            "armor_loot_box",
            () -> new ArmorLootBoxItem(new Item.Properties())
    );

    public static final DeferredHolder<Item, Item> WEAPON_LOOT_BOX = ITEMS.register(
            "weapon_loot_box",
            () -> new WeaponLootBoxItem(new Item.Properties())
    );

    public static final DeferredHolder<Item, Item> TOOL_LOOT_BOX = ITEMS.register(
            "tool_loot_box",
            () -> new ToolLootBoxItem(new Item.Properties())
    );

    public static final DeferredHolder<Item, Item> FOOD_LOOT_BOX = ITEMS.register(
            "food_loot_box",
            () -> new FoodLootBoxItem(new Item.Properties())
    );

    public static final DeferredHolder<Item, Item> POTION_LOOT_BOX = ITEMS.register(
            "potion_loot_box",
            () -> new PotionLootBoxItem(new Item.Properties())
    );


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void registerCommon() {
        ModRegistryAccess.addItem("armor_loot_box", TraderItemsNeoForge.ARMOR_LOOT_BOX.get());
        ModRegistryAccess.addItem("weapon_loot_box", TraderItemsNeoForge.WEAPON_LOOT_BOX.get());
        ModRegistryAccess.addItem("tool_loot_box", TraderItemsNeoForge.TOOL_LOOT_BOX.get());
        ModRegistryAccess.addItem("food_loot_box", TraderItemsNeoForge.FOOD_LOOT_BOX.get());
        ModRegistryAccess.addItem("potion_loot_box", TraderItemsNeoForge.POTION_LOOT_BOX.get());
    }

}
