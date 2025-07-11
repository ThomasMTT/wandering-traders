package life.thoms.wandering_traders.util;

import net.minecraft.world.item.*;

public class LootFilters {

    public static boolean isImportantLoot(ItemStack stack) {
        return !isExcludedItem(stack) && (isEnchanted(stack) || isEnchantedBook(stack) || isValuableItem(stack));
    }

    private static boolean isValuableItem(ItemStack stack) {
        return  isWeapon(stack) || isValuableArmor(stack) || isValuableTool(stack) || isEffectPotion(stack) ||
                isValuableBlock(stack) || isValuableIngot(stack);
    }


    public static boolean isExcludedItem(ItemStack stack) {
        return (Items.EMERALD == stack.getItem() || Items.AIR == stack.getItem());
    }

    public static boolean isEnchanted(ItemStack stack) {
        return stack.isEnchanted();
    }

    public static boolean isEnchantedBook(ItemStack stack) {
        return stack.getItem() instanceof EnchantedBookItem;
    }

    public static boolean isWeapon(ItemStack stack) {
        Item item = stack.getItem();

        if (item instanceof SwordItem swordItem) {
            Tier material = swordItem.getTier();
            return !(material.equals(Tiers.WOOD) || material.equals(Tiers.STONE) ||
                    material.equals(Tiers.IRON) || material.equals(Tiers.GOLD));
        }

        return (item instanceof BowItem || item instanceof CrossbowItem || item instanceof TridentItem);
    }

    public static boolean isValuableArmor(ItemStack stack) {
        Item armor = stack.getItem();
        if (armor instanceof ElytraItem) {
            return true;
        }

        if (armor.toString().contains("wolf_armor") || armor.toString().contains("horse_armor")) {
            return false;
        }

        if (armor instanceof ArmorItem) {
            ArmorMaterial material = ((ArmorItem) armor).getMaterial();
            return !(material.equals(ArmorMaterials.LEATHER) || material.equals(ArmorMaterials.CHAIN) ||
                    material.equals(ArmorMaterials.IRON) || material.equals(ArmorMaterials.GOLD));
        }
        return false;
    }

    public static boolean isValuableTool(ItemStack stack) {
        Item item = stack.getItem();

        if (item instanceof FishingRodItem) {
            return true;
        }

        if (item instanceof DiggerItem toolItem) {
            Tier material = toolItem.getTier();
            return !(material.equals(Tiers.WOOD) || material.equals(Tiers.STONE) ||
                    material.equals(Tiers.IRON) || material.equals(Tiers.GOLD));
        }
        return false;
    }

    public static boolean isValuableBlock(ItemStack stack) {
        Item item = stack.getItem();
        return (item == Items.DIAMOND_BLOCK || item == Items.NETHERITE_BLOCK || item == Items.ANCIENT_DEBRIS);
    }

    public static boolean isValuableIngot(ItemStack stack) {
        Item item = stack.getItem();
        return (item == Items.DIAMOND || item == Items.NETHERITE_INGOT || item == Items.NETHERITE_SCRAP ||
                item == Items.NETHER_STAR || item == Items.GOLDEN_APPLE || item == Items.ENCHANTED_GOLDEN_APPLE ||
                item == Items.ENDER_EYE);
    }

    public static boolean isEffectPotion(ItemStack stack) {
        return stack.getItem() instanceof PotionItem;
    }


}
