package life.thoms.wandering_traders.util;

import life.thoms.wandering_traders.server.ModRegistryAccess;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.block.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MerchantUtil {

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();
    public static final List<Potion> POTIONS = new ArrayList<>();

    public static final List<Item> WEAPON_ITEMS = new ArrayList<>();
    public static final List<Item> ARMOR_ITEMS = new ArrayList<>();
    public static final List<Item> TOOL_ITEMS = new ArrayList<>();
    public static final List<Item> FOOD_ITEMS = new ArrayList<>();
    public static final List<Item> SAPPLING_ITEMS = new ArrayList<>();
    public static final List<Item> ANIMAL_SPAWN_EGG_ITEMS = new ArrayList<>();

    public static final MerchantOffers LOOT_BOX_OFFERS = new MerchantOffers();

    public static void register(ServerLevel level) {
        LOOT_BOX_OFFERS.clear();
        LOOT_BOX_OFFERS.add(createOffer(ModRegistryAccess.ITEM_ACCESS.get("food_loot_box"), 2, 32));
        LOOT_BOX_OFFERS.add(createOffer(ModRegistryAccess.ITEM_ACCESS.get("potion_loot_box"), 4, 16));
        LOOT_BOX_OFFERS.add(createOffer(ModRegistryAccess.ITEM_ACCESS.get("armor_loot_box"), 16, 4));
        LOOT_BOX_OFFERS.add(createOffer(ModRegistryAccess.ITEM_ACCESS.get("weapon_loot_box"), 16, 4));
        LOOT_BOX_OFFERS.add(createOffer(ModRegistryAccess.ITEM_ACCESS.get("tool_loot_box"), 16, 4));

        List<ResourceLocation> enchantmentLocations = BuiltInRegistries.ENCHANTMENT.keySet().stream().toList();
        for (ResourceLocation enchantmentLocation : enchantmentLocations) {
            ENCHANTMENTS.add(BuiltInRegistries.ENCHANTMENT.get(enchantmentLocation));
        }

        List<ResourceLocation> potionLocations = BuiltInRegistries.POTION.keySet().stream().toList();
        for (ResourceLocation potionLocation : potionLocations) {
            POTIONS.add(BuiltInRegistries.POTION.get(potionLocation));
        }
        Animal fakeAnimal;

        Set<ResourceLocation> itemLocations = BuiltInRegistries.ITEM.keySet();
        for (ResourceLocation itemLocation : itemLocations) {
            Item item = BuiltInRegistries.ITEM.get(itemLocation);
            if (item instanceof ArmorItem) {
                ARMOR_ITEMS.add(item);
            } else if (item instanceof DiggerItem || item instanceof ShearsItem || item instanceof FlintAndSteelItem ||
                    item instanceof SpyglassItem || item instanceof CompassItem || item instanceof FishingRodItem) {
                TOOL_ITEMS.add(item);
            } else if (item instanceof SwordItem || item instanceof BowItem || item instanceof CrossbowItem ||
                    item instanceof TridentItem || item instanceof ShieldItem) {
                WEAPON_ITEMS.add(item);
            } else if (new ItemStack(item).getComponents().has(DataComponents.FOOD)) {
                FOOD_ITEMS.add(item);
            } else if (item instanceof SpawnEggItem eggItem) {
                Entity entity = eggItem.getType(new ItemStack(eggItem)).create(level);
                // Dissable hoglin
                if (entity instanceof Hoglin) break;
                if (entity != null) {
                    if (entity instanceof Animal) {
                        ANIMAL_SPAWN_EGG_ITEMS.add(item);
                    }
                    entity.discard();
                }
            }
            if (item instanceof BlockItem blockItem) {
                Block block = blockItem.getBlock();
                if (block instanceof SaplingBlock) {
                    SAPPLING_ITEMS.add(item);
                }
            }
        }
    }

    public static ItemStack generateStackFromItem(Random random, Item item) {
        ItemStack stack = new ItemStack(item);

        if (stack.isEnchantable() && (ARMOR_ITEMS.contains(item) || TOOL_ITEMS.contains(item) ||
                WEAPON_ITEMS.contains(item) || item instanceof EnchantedBookItem)) {
            // Determine if item should be enchanted and how many times
            int enchantmentChance = random.nextInt(100);
            int enchantments = 0;
            if (enchantmentChance > 90) enchantments = 3;
            else if (enchantmentChance > 80) enchantments = 2;
            else if (enchantmentChance > 60) enchantments = 1;

            // Apply random compatible enchantments (max 20 tries per enchantment (if reached returns the item as is)
            if (enchantments > 0) {

                int i = 0;
                while (enchantments > 0 && i < 20) {
                    Enchantment enchantment = ENCHANTMENTS.get(random.nextInt(ENCHANTMENTS.size()));
                    if (enchantment.canEnchant(stack)) {
                        stack.enchant(enchantment, random.nextInt(enchantment.getMaxLevel()));
                        enchantments -= 1;
                        i = 0;
                    }
                    i++;
                }
            }
        } else if (item instanceof PotionItem) {
            item.components();
            Potion potion = POTIONS.get(random.nextInt(POTIONS.size()));
            stack = PotionContents.createItemStack(stack.getItem(), Holder.direct(potion));
        }
        return stack;
    }

    private static MerchantOffer createOffer(Item lootBoxItem, int price, int maxUses) {
        return new MerchantOffer(
                new ItemCost(Items.EMERALD, price),
                new ItemStack(lootBoxItem),
                maxUses, 1, 1
        );
    }

}
