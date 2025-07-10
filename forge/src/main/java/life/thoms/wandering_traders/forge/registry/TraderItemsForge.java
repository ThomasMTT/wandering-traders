package life.thoms.wandering_traders.forge.registry;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.item.EndBellItem;
import life.thoms.wandering_traders.server.ModRegistryAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TraderItemsForge {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            WanderingTraders.MOD_ID
    );

    // Spawn Eggs
    public static final RegistryObject<ForgeSpawnEggItem> END_TRADER_SPAWN_EGG = ITEMS.register(
            "end_trader_spawn_egg",
            () -> new ForgeSpawnEggItem(TraderEntitiesForge.END_TRADER,
                    0x5e0da5, 0xcea512, new Item.Properties())
    );

    public static final RegistryObject<SpawnEggItem> DECORATION_TRADER_SPAWN_EGG = ITEMS.register(
            "decoration_trader_spawn_egg",
            () -> new ForgeSpawnEggItem(TraderEntitiesForge.DECORATION_TRADER,
                    0xeaeef5, 0xcea512, new Item.Properties())
    );

    public static final RegistryObject<SpawnEggItem> BOOK_TRADER_SPAWN_EGG = ITEMS.register(
            "book_trader_spawn_egg",
            () -> new ForgeSpawnEggItem(TraderEntitiesForge.BOOK_TRADER,
                    0x435f91, 0xff8c00, new Item.Properties())
    );

    public static final RegistryObject<SpawnEggItem> FOREST_TRADER_SPAWN_EGG = ITEMS.register(
            "forest_trader_spawn_egg",
            () -> new ForgeSpawnEggItem(TraderEntitiesForge.FOREST_TRADER,
                    0x008000, 0xcea512, new Item.Properties())
    );

    public static final RegistryObject<SpawnEggItem> POTION_TRADER_SPAWN_EGG = ITEMS.register(
            "potion_trader_spawn_egg",
            () -> new ForgeSpawnEggItem(TraderEntitiesForge.POTION_TRADER,
                    0x953298, 0xcac9c9, new Item.Properties())
    );

    public static final RegistryObject<SpawnEggItem> ANIMAL_TRADER_SPAWN_EGG = ITEMS.register(
            "animal_trader_spawn_egg",
            () -> new ForgeSpawnEggItem(TraderEntitiesForge.ANIMAL_TRADER,
                    0x614625, 0xcac9c9, new Item.Properties())
    );

    public static final RegistryObject<SpawnEggItem> EXCLUSIVE_TRADER_SPAWN_EGG = ITEMS.register(
            "exclusive_trader_spawn_egg",
            () -> new ForgeSpawnEggItem(TraderEntitiesForge.EXCLUSIVE_TRADER,
                    0x421111, 0xcc8e29, new Item.Properties())
    );

    public static final RegistryObject<Item> END_BELL = ITEMS.register(
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
