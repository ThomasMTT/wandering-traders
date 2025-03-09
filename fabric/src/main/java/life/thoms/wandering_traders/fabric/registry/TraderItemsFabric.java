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

    public static final Item GAMBLING_TRADER_SPAWN_EGG = register("gambling_trader_spawn_egg",
            new SpawnEggItem(TraderEntitiesFabric.GAMBLING_TRADER, 0xeaeef5, 0xcea512, new Item.Properties()));

    public static final Item BOOK_TRADER_SPAWN_EGG = register("book_trader_spawn_egg",
            new SpawnEggItem(TraderEntitiesFabric.BOOK_TRADER, 0x87cefa, 0xff8c00, new Item.Properties()));

    // Loot Boxes
    public static final Item ARMOR_LOOT_BOX = register("armor_loot_box",
            new ArmorLootBoxItem(new Item.Properties()));

    public static final Item WEAPON_LOOT_BOX = register("weapon_loot_box",
            new WeaponLootBoxItem(new Item.Properties()));

    public static final Item TOOL_LOOT_BOX = register("tool_loot_box",
            new ToolLootBoxItem(new Item.Properties()));

    public static final Item FOOD_LOOT_BOX = register("food_loot_box",
            new FoodLootBoxItem(new Item.Properties()));

    public static final Item POTION_LOOT_BOX = register("potion_loot_box",
            new PotionLootBoxItem(new Item.Properties()));

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(WanderingTraders.MOD_ID + ":" + name), item);
    }

    // (Statically Initialized, do not remove)
    public static void register() {
        registerCommon();
    }

    // Makes items accessible in common code
    public static void registerCommon() {
        ModRegistryAccess.addItem("armor_loot_box", ARMOR_LOOT_BOX);
        ModRegistryAccess.addItem("weapon_loot_box", WEAPON_LOOT_BOX);
        ModRegistryAccess.addItem("tool_loot_box", TOOL_LOOT_BOX);
        ModRegistryAccess.addItem("food_loot_box", FOOD_LOOT_BOX);
        ModRegistryAccess.addItem("potion_loot_box", POTION_LOOT_BOX);
    }

}
