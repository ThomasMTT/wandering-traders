package life.thoms.wandering_traders.neoforge.registry;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.item.*;
import life.thoms.wandering_traders.item.lootbox.*;
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

    public static final DeferredHolder<Item, DeferredSpawnEggItem> DECORATION_TRADER_SPAWN_EGG = ITEMS.register(
            "decoration_trader_spawn_egg",
            () -> new DeferredSpawnEggItem(TraderEntitiesNeoForge.DECORATION_TRADER,
                    0xeaeef5, 0xcea512, new Item.Properties())
    );

    public static final DeferredHolder<Item, DeferredSpawnEggItem> BOOK_TRADER_SPAWN_EGG = ITEMS.register(
            "book_trader_spawn_egg",
            () -> new DeferredSpawnEggItem(TraderEntitiesNeoForge.BOOK_TRADER,
                    0x435f91, 0xff8c00, new Item.Properties())
    );

    public static final DeferredHolder<Item, DeferredSpawnEggItem> FOREST_TRADER_SPAWN_EGG = ITEMS.register(
            "forest_trader_spawn_egg",
            () -> new DeferredSpawnEggItem(TraderEntitiesNeoForge.FOREST_TRADER,
                    0x008000, 0xcea512, new Item.Properties())
    );

    public static final DeferredHolder<Item, DeferredSpawnEggItem> POTION_TRADER_SPAWN_EGG = ITEMS.register(
            "potion_trader_spawn_egg",
            () -> new DeferredSpawnEggItem(TraderEntitiesNeoForge.POTION_TRADER,
                    0x953298, 0xcac9c9, new Item.Properties())
    );

    public static final DeferredHolder<Item, DeferredSpawnEggItem> ANIMAL_TRADER_SPAWN_EGG = ITEMS.register(
            "animal_trader_spawn_egg",
            () -> new DeferredSpawnEggItem(TraderEntitiesNeoForge.ANIMAL_TRADER,
                    0x614625, 0xcac9c9, new Item.Properties())
    );

    public static final DeferredHolder<Item, DeferredSpawnEggItem> EXCLUSIVE_TRADER_SPAWN_EGG = ITEMS.register(
            "exclusive_trader_spawn_egg",
            () -> new DeferredSpawnEggItem(TraderEntitiesNeoForge.EXCLUSIVE_TRADER,
                    0x421111, 0xcc8e29, new Item.Properties())
    );

    public static final DeferredHolder<Item, Item> END_BELL = ITEMS.register(
            "end_bell",
            () -> new EndBellItem(new Item.Properties())
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void registerCommon() {
        ModRegistryAccess.addItem("end_bell", END_BELL.get());
    }

}
