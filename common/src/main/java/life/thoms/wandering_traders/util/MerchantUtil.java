package life.thoms.wandering_traders.util;

import life.thoms.wandering_traders.server.ModRegistryAccess;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.block.*;

import java.util.*;

public class MerchantUtil {

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();
    public static final List<Potion> POTIONS = new ArrayList<>();

    public static final List<Item> WEAPON_ITEMS = new ArrayList<>();
    public static final List<Item> ARMOR_ITEMS = new ArrayList<>();
    public static final List<Item> TOOL_ITEMS = new ArrayList<>();
    public static final List<Item> FOOD_ITEMS = new ArrayList<>();
    public static final List<Item> SAPPLING_ITEMS = new ArrayList<>();
    public static final List<Item> ANIMAL_SPAWN_EGG_ITEMS = new ArrayList<>();

    public static final Map<Item, Integer> EXCLUSIVE_ITEMS = new HashMap<>();
    public static final List<Item> EXCLUSIVE_ITEMS_LIST = new ArrayList<>();;

    public static final MerchantOffers LOOT_BOX_OFFERS = new MerchantOffers();

    public static void register(ServerLevel level) {
        LOOT_BOX_OFFERS.clear();
        registerLootBoxOffers();
        registerEnchantments();
        registerPotions();
        registerItems(level);
        registerExclusiveItems();
    }

    private static void registerLootBoxOffers() {
        LOOT_BOX_OFFERS.add(createOffer(ModRegistryAccess.ITEM_ACCESS.get("food_loot_box"), 2, 32));
        LOOT_BOX_OFFERS.add(createOffer(ModRegistryAccess.ITEM_ACCESS.get("potion_loot_box"), 4, 16));
        LOOT_BOX_OFFERS.add(createOffer(ModRegistryAccess.ITEM_ACCESS.get("armor_loot_box"), 16, 4));
        LOOT_BOX_OFFERS.add(createOffer(ModRegistryAccess.ITEM_ACCESS.get("weapon_loot_box"), 16, 4));
        LOOT_BOX_OFFERS.add(createOffer(ModRegistryAccess.ITEM_ACCESS.get("tool_loot_box"), 16, 4));
    }

    private static void registerEnchantments() {
        List<ResourceLocation> enchantmentLocations = BuiltInRegistries.ENCHANTMENT.keySet().stream().toList();
        for (ResourceLocation enchantmentLocation : enchantmentLocations) {
            ENCHANTMENTS.add(BuiltInRegistries.ENCHANTMENT.get(enchantmentLocation));
        }
    }

    private static void registerPotions() {
        List<ResourceLocation> potionLocations = BuiltInRegistries.POTION.keySet().stream().toList();
        for (ResourceLocation potionLocation : potionLocations) {
            POTIONS.add(BuiltInRegistries.POTION.get(potionLocation));
        }
    }

    private static void registerItems(ServerLevel level) {
        Set<ResourceLocation> itemLocations = BuiltInRegistries.ITEM.keySet();
        for (ResourceLocation itemLocation : itemLocations) {
            Item item = BuiltInRegistries.ITEM.get(itemLocation);
            categorizeItem(level, item);
        }
    }

    public static void registerExclusiveItems() {
        EXCLUSIVE_ITEMS.put(ModRegistryAccess.ITEM_ACCESS.get("end_bell"), 16);
        EXCLUSIVE_ITEMS.put(Items.TOTEM_OF_UNDYING, 32);
        EXCLUSIVE_ITEMS.put(Items.ENCHANTED_GOLDEN_APPLE, 20);
        EXCLUSIVE_ITEMS.put(Items.WITHER_SKELETON_SKULL, 48);
        EXCLUSIVE_ITEMS.put(Items.NAME_TAG, 12);
        EXCLUSIVE_ITEMS.put(Items.SADDLE, 8);
        EXCLUSIVE_ITEMS.put(Items.DIAMOND_HORSE_ARMOR, 12);
        EXCLUSIVE_ITEMS_LIST.addAll(EXCLUSIVE_ITEMS.keySet().stream().toList());
    }

    private static void categorizeItem(ServerLevel level, Item item) {
        if (item instanceof ArmorItem) {
            ARMOR_ITEMS.add(item);
        } else if (isToolItem(item)) {
            TOOL_ITEMS.add(item);
        } else if (isWeaponItem(item)) {
            WEAPON_ITEMS.add(item);
        } else if (new ItemStack(item).getComponents().has(DataComponents.FOOD)) {
            FOOD_ITEMS.add(item);
        } else if (item instanceof SpawnEggItem eggItem) {
            handleSpawnEggItem(level, eggItem);
        }
        if (item instanceof BlockItem blockItem) {
            handleBlockItem(blockItem);
        }
    }

    private static boolean isToolItem(Item item) {
        return item instanceof DiggerItem || item instanceof ShearsItem ||
                item instanceof FlintAndSteelItem || item instanceof SpyglassItem ||
                item instanceof CompassItem || item instanceof FishingRodItem;
    }

    private static boolean isWeaponItem(Item item) {
        return item instanceof SwordItem || item instanceof BowItem ||
                item instanceof CrossbowItem || item instanceof TridentItem ||
                item instanceof ShieldItem;
    }

    private static void handleSpawnEggItem(ServerLevel level, SpawnEggItem eggItem) {
        Entity entity = eggItem.getType(new ItemStack(eggItem)).create(level);
        // Disable Hoglin
        if (entity instanceof Hoglin) return;
        if (entity != null) {
            if (entity instanceof Animal) {
                ANIMAL_SPAWN_EGG_ITEMS.add(eggItem);
            }
            entity.discard();
        }
    }

    private static void handleBlockItem(BlockItem blockItem) {
        Block block = blockItem.getBlock();
        if (block instanceof SaplingBlock) {
            SAPPLING_ITEMS.add(blockItem);
        }
    }


    public static ItemStack generateStackFromItem(Random random, Item item) {
        ItemStack stack = new ItemStack(item);

        if (shouldEnchantItem(stack, item)) {
            applyRandomEnchantments(random, stack);
        } else if (item instanceof PotionItem) {
            stack = createPotionStack(random, stack);
        }

        return stack;
    }

    private static boolean shouldEnchantItem(ItemStack stack, Item item) {
        return stack.isEnchantable() && (ARMOR_ITEMS.contains(item) || TOOL_ITEMS.contains(item) ||
                WEAPON_ITEMS.contains(item) || item instanceof EnchantedBookItem);
    }

    private static void applyRandomEnchantments(Random random, ItemStack stack) {
        int enchantmentChance = random.nextInt(100);
        int enchantments = determineEnchantmentCount(enchantmentChance);

        if (enchantments > 0) {
            int attempts = 0;
            while (enchantments > 0 && attempts < 20) {
                Enchantment enchantment = ENCHANTMENTS.get(random.nextInt(ENCHANTMENTS.size()));
                if (enchantment.canEnchant(stack)) {
                    stack.enchant(enchantment, random.nextInt(enchantment.getMaxLevel()) + 1); // +1 to ensure at least level 1
                    enchantments -= 1;
                    attempts = 0; // Reset attempts after a successful enchantment
                }
                attempts++;
            }
        }
    }

    private static int determineEnchantmentCount(int enchantmentChance) {
        if (enchantmentChance > 90) return 3;
        else if (enchantmentChance > 80) return 2;
        else if (enchantmentChance > 60) return 1;
        return 0;
    }

    private static ItemStack createPotionStack(Random random, ItemStack stack) {
        Potion potion = POTIONS.get(random.nextInt(POTIONS.size()));
        return PotionContents.createItemStack(stack.getItem(), Holder.direct(potion));
    }

    private static MerchantOffer createOffer(Item lootBoxItem, int price, int maxUses) {
        return new MerchantOffer(
                new ItemCost(Items.EMERALD, price),
                new ItemStack(lootBoxItem),
                maxUses, 1, 1
        );
    }

}
