package life.thoms.wandering_traders.util;

import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LootFiltersTest {

    @BeforeAll
    public static void beforeAll() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
    }

    @Test
    public void isExcludedItem() {
        ItemStack stack = new ItemStack(Items.EMERALD);
        assertTrue(LootFilters.isExcludedItem(stack));
    }

    @Test
    public void isNotExcludedItem() {
        ItemStack stack = new ItemStack(Items.GLASS);
        assertFalse(LootFilters.isExcludedItem(stack));
    }

    @Test
    public void isEnchantedItem() {
        ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
        stack.enchant(Enchantments.SHARPNESS, 1);
        assertTrue(LootFilters.isEnchanted(stack));
    }

    @Test
    public void isNotEnchantedItem() {
        ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
        assertFalse(LootFilters.isEnchanted(stack));
    }

    @Test
    public void isEnchantedBook() {
        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        assertTrue(LootFilters.isEnchantedBook(stack));
    }

    @Test
    public void isNotEnchantedBook() {
        ItemStack stack = new ItemStack(Items.BOOK);
        assertFalse(LootFilters.isEnchantedBook(stack));
    }

    @Test
    public void isWeapon() {
        ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
        assertTrue(LootFilters.isWeapon(stack));
    }

    @Test
    public void isNotWeapon() {
        ItemStack stack = new ItemStack(Items.DIAMOND);
        assertFalse(LootFilters.isWeapon(stack));
    }

    @Test
    public void isValuableArmor() {
        ItemStack stack = new ItemStack(Items.DIAMOND_CHESTPLATE);
        assertTrue(LootFilters.isValuableArmor(stack));
    }

    @Test
    public void isNotValuableArmor() {
        ItemStack stack = new ItemStack(Items.LEATHER_CHESTPLATE);
        assertFalse(LootFilters.isValuableArmor(stack));
    }

    @Test
    public void isValuableTool() {
        ItemStack stack = new ItemStack(Items.DIAMOND_PICKAXE);
        assertTrue(LootFilters.isValuableTool(stack));
    }

    @Test
    public void isNotValuableTool() {
        ItemStack stack = new ItemStack(Items.WOODEN_PICKAXE);
        assertFalse(LootFilters.isValuableTool(stack));
    }

    @Test
    public void isValuableBlock() {
        ItemStack stack = new ItemStack(Items.DIAMOND_BLOCK);
        assertTrue(LootFilters.isValuableBlock(stack));
    }

    @Test
    public void isNotValuableBlock() {
        ItemStack stack = new ItemStack(Items.STONE);
        assertFalse(LootFilters.isValuableBlock(stack));
    }

    @Test
    public void isValuableIngot() {
        ItemStack stack = new ItemStack(Items.DIAMOND);
        assertTrue(LootFilters.isValuableIngot(stack));
    }

    @Test
    public void isNotValuableIngot() {
        ItemStack stack = new ItemStack(Items.IRON_INGOT);
        assertFalse(LootFilters.isValuableIngot(stack));
    }

    @Test
    public void isEffectPotion() {
        ItemStack stack = new ItemStack(Items.POTION);
        assertTrue(LootFilters.isEffectPotion(stack));
    }

    @Test
    public void isNotEffectPotion() {
        ItemStack stack = new ItemStack(Items.WATER_BUCKET);
        assertFalse(LootFilters.isEffectPotion(stack));
    }

/*
    @Test
    public void isInConfig() {
        ConfigManager restoreConfig = ConfigManager.CONFIG;

        ConfigManager.CONFIG = Mockito.mock(ConfigManager.class);
        Mockito.when(ConfigManager.CONFIG.getValuable_items()).thenReturn(List.of("minecraft:diamond_sword"));
        ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
        assertTrue(LootFilters.isInConfig(stack));

        ConfigManager.CONFIG = restoreConfig;
    }

    @Test
    public void isInNotConfig() {
        ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
        assertFalse(LootFilters.isInConfig(stack));
    }*/

}