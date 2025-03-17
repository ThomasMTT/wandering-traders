package life.thoms.wandering_traders.fabric.registry;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.item.*;
import life.thoms.wandering_traders.server.ModRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class TraderItemsFabric {

    // Spawn Eggs
    public static final Item END_TRADER_SPAWN_EGG = register("end_trader_spawn_egg",
            new SpawnEggItem(TraderEntitiesFabric.END_TRADER, 0x5e0da5, 0xcea512, new Item.Properties()));

    public static final Item DECORATION_TRADER_SPAWN_EGG = register("decoration_trader_spawn_egg",
            new SpawnEggItem(TraderEntitiesFabric.DECORATION_TRADER, 0xeaeef5, 0xcea512, new Item.Properties()));

    public static final Item BOOK_TRADER_SPAWN_EGG = register("book_trader_spawn_egg",
            new SpawnEggItem(TraderEntitiesFabric.BOOK_TRADER, 0x435f91, 0xff8c00, new Item.Properties()));

    public static final Item FOREST_TRADER_SPAWN_EGG = register("forest_trader_spawn_egg",
            new SpawnEggItem(TraderEntitiesFabric.FOREST_TRADER, 0x008000, 0xcea512, new Item.Properties()));

    public static final Item POTION_TRADER_SPAWN_EGG = register("potion_trader_spawn_egg",
            new SpawnEggItem(TraderEntitiesFabric.POTION_TRADER, 0x953298, 0xcac9c9, new Item.Properties()));

    public static final Item ANIMAL_TRADER_SPAWN_EGG = register("animal_trader_spawn_egg",
            new SpawnEggItem(TraderEntitiesFabric.ANIMAL_TRADER, 0x614625, 0xcac9c9, new Item.Properties()));

    public static final Item EXCLUSIVE_TRADER_SPAWN_EGG = register("exclusive_trader_spawn_egg",
            new SpawnEggItem(TraderEntitiesFabric.EXCLUSIVE_TRADER, 0x421111, 0xcc8e29, new Item.Properties()));


    public static final Item END_BELL = register("end_bell",
            new EndBellItem(new Item.Properties()));

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(WanderingTraders.MOD_ID + ":" + name), item);
    }

    // (Statically Initialized, do not remove)
    public static void register() {
        registerCommon();
    }

    // Makes items accessible in common code
    public static void registerCommon() {
        ModRegistryAccess.addItem("end_bell", END_BELL);
    }

}
