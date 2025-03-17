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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;

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
    public static final List<Item> EXCLUSIVE_ITEMS_LIST = new ArrayList<>();
    ;

    public static final Map<Item, Integer> DECORATIVE_ITEMS = new LinkedHashMap<>();
    public static final List<Item> DECORATIVE_ITEMS_LIST = new ArrayList<>();
    ;

    public static void register(ServerLevel level) {
        registerDecorativeItems();
        registerEnchantments();
        registerPotions();
        registerItems(level);
        registerExclusiveItems();
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

    public static MerchantOffer createOffer(Item lootBoxItem, int price, int maxUses) {
        return createOffer(new ItemStack(lootBoxItem), price, maxUses);
    }

    public static MerchantOffer createOffer(ItemStack lootBoxItem, int price, int maxUses) {
        return new MerchantOffer(
                new ItemCost(Items.EMERALD, price),
                lootBoxItem,
                maxUses, 1, 1
        );
    }

    public static void sortOffers(MerchantOffers offers, boolean reversed) {
        int rev = reversed ? -1 : 1;
        offers.sort((x, z) -> rev * Integer.compare(z.getItemCostA().count(), x.getItemCostA().count()));
    }

    public static void sortOffers(MerchantOffers offers) {
        sortOffers(offers, false);
    }

    public static void registerDecorativeItems() {
        int carpetPrice = 1;
        int woolPrice = 2;
        int glassPrice = 2;
        int terracottaPrice = 2;
        int glazedTerracottaPrice = 3;
        int concretePrice = 3;
        int bannerPrice = 6;

        addCarpets(carpetPrice);
        addWool(woolPrice);
        DECORATIVE_ITEMS.put(Items.QUARTZ_BLOCK, 2);
        DECORATIVE_ITEMS.put(Items.BRICKS, 2);
        addGlass(glassPrice);
        addTerracotta(terracottaPrice);
        addGlazedTerracotta(glazedTerracottaPrice);
        addConcrete(concretePrice);
        addFramesAndPaintings();
        addBanners(bannerPrice);

        // Add all items to the list
        DECORATIVE_ITEMS_LIST.addAll(DECORATIVE_ITEMS.keySet().stream().toList());
    }

    private static void addCarpets(int price) {
        // Add Carpets
        DECORATIVE_ITEMS.put(Items.WHITE_CARPET, price);
        DECORATIVE_ITEMS.put(Items.ORANGE_CARPET, price);
        DECORATIVE_ITEMS.put(Items.MAGENTA_CARPET, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_BLUE_CARPET, price);
        DECORATIVE_ITEMS.put(Items.YELLOW_CARPET, price);
        DECORATIVE_ITEMS.put(Items.LIME_CARPET, price);
        DECORATIVE_ITEMS.put(Items.PINK_CARPET, price);
        DECORATIVE_ITEMS.put(Items.GRAY_CARPET, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_GRAY_CARPET, price);
        DECORATIVE_ITEMS.put(Items.CYAN_CARPET, price);
        DECORATIVE_ITEMS.put(Items.PURPLE_CARPET, price);
        DECORATIVE_ITEMS.put(Items.BLUE_CARPET, price);
        DECORATIVE_ITEMS.put(Items.BROWN_CARPET, price);
        DECORATIVE_ITEMS.put(Items.GREEN_CARPET, price);
        DECORATIVE_ITEMS.put(Items.RED_CARPET, price);
        DECORATIVE_ITEMS.put(Items.BLACK_CARPET, price);
    }

    private static void addWool(int price) {
        // Add Wool
        DECORATIVE_ITEMS.put(Items.WHITE_WOOL, price);
        DECORATIVE_ITEMS.put(Items.ORANGE_WOOL, price);
        DECORATIVE_ITEMS.put(Items.MAGENTA_WOOL, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_BLUE_WOOL, price);
        DECORATIVE_ITEMS.put(Items.YELLOW_WOOL, price);
        DECORATIVE_ITEMS.put(Items.LIME_WOOL, price);
        DECORATIVE_ITEMS.put(Items.PINK_WOOL, price);
        DECORATIVE_ITEMS.put(Items.GRAY_WOOL, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_GRAY_WOOL, price);
        DECORATIVE_ITEMS.put(Items.CYAN_WOOL, price);
        DECORATIVE_ITEMS.put(Items.PURPLE_WOOL, price);
        DECORATIVE_ITEMS.put(Items.BLUE_WOOL, price);
        DECORATIVE_ITEMS.put(Items.BROWN_WOOL, price);
        DECORATIVE_ITEMS.put(Items.GREEN_WOOL, price);
        DECORATIVE_ITEMS.put(Items.RED_WOOL, price);
        DECORATIVE_ITEMS.put(Items.BLACK_WOOL, price);
    }

    private static void addGlass(int price) {
        // Add Glass
        DECORATIVE_ITEMS.put(Items.GLASS, 2);
        DECORATIVE_ITEMS.put(Items.WHITE_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.ORANGE_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.MAGENTA_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_BLUE_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.YELLOW_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.LIME_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.PINK_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.GRAY_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_GRAY_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.CYAN_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.PURPLE_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.BLUE_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.BROWN_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.GREEN_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.RED_STAINED_GLASS, price);
        DECORATIVE_ITEMS.put(Items.BLACK_STAINED_GLASS, price);
    }

    private static void addTerracotta(int price) {
        // Add colored Terracotta
        DECORATIVE_ITEMS.put(Items.TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.WHITE_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.ORANGE_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.MAGENTA_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_BLUE_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.YELLOW_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.LIME_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.PINK_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.GRAY_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_GRAY_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.CYAN_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.PURPLE_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.BLUE_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.BROWN_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.GREEN_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.RED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.BLACK_TERRACOTTA, price);
    }

    private static void addGlazedTerracotta(int price) {
        // Add Glazed Terracotta
        DECORATIVE_ITEMS.put(Items.WHITE_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.ORANGE_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.MAGENTA_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_BLUE_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.YELLOW_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.LIME_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.PINK_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.GRAY_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_GRAY_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.CYAN_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.PURPLE_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.BLUE_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.BROWN_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.GREEN_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.RED_GLAZED_TERRACOTTA, price);
        DECORATIVE_ITEMS.put(Items.BLACK_GLAZED_TERRACOTTA, price);
    }

    private static void addConcrete(int price) {
        // Add Concrete
        DECORATIVE_ITEMS.put(Items.WHITE_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.ORANGE_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.MAGENTA_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_BLUE_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.YELLOW_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.LIME_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.PINK_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.GRAY_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_GRAY_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.CYAN_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.PURPLE_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.BLUE_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.BROWN_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.GREEN_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.RED_CONCRETE, price);
        DECORATIVE_ITEMS.put(Items.BLACK_CONCRETE, price);
    }

    private static void addFramesAndPaintings() {
        // Add Item Frame and Painting
        DECORATIVE_ITEMS.put(Items.ITEM_FRAME, 4);
        DECORATIVE_ITEMS.put(Items.PAINTING, 4);
    }

    private static void addBanners(int price) {
        // Add Banners
        DECORATIVE_ITEMS.put(Items.WHITE_BANNER, price);
        DECORATIVE_ITEMS.put(Items.ORANGE_BANNER, price);
        DECORATIVE_ITEMS.put(Items.MAGENTA_BANNER, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_BLUE_BANNER, price);
        DECORATIVE_ITEMS.put(Items.YELLOW_BANNER, price);
        DECORATIVE_ITEMS.put(Items.LIME_BANNER, price);
        DECORATIVE_ITEMS.put(Items.PINK_BANNER, price);
        DECORATIVE_ITEMS.put(Items.GRAY_BANNER, price);
        DECORATIVE_ITEMS.put(Items.LIGHT_GRAY_BANNER, price);
        DECORATIVE_ITEMS.put(Items.CYAN_BANNER, price);
        DECORATIVE_ITEMS.put(Items.PURPLE_BANNER, price);
        DECORATIVE_ITEMS.put(Items.BLUE_BANNER, price);
        DECORATIVE_ITEMS.put(Items.BROWN_BANNER, price);
        DECORATIVE_ITEMS.put(Items.GREEN_BANNER, price);
        DECORATIVE_ITEMS.put(Items.RED_BANNER, price);
        DECORATIVE_ITEMS.put(Items.BLACK_BANNER, price);
    }

}
