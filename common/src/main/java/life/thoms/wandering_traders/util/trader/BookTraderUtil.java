package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class BookTraderUtil {

    public static final Random RANDOM = new Random();

    public static MerchantOffers generateOffers() {
        MerchantOffers offers = new MerchantOffers();
        offers.clear();

        for (int i = 0; i < 10; i++) {
            offers.add(createOffer());
        }
        MerchantUtil.sortOffers(offers);
        return offers;
    }

    private static MerchantOffer createOffer() {
        ItemStack enchantedBook = generateStackFromItem(RANDOM);
        ItemEnchantments enchantments = enchantedBook.getComponents().get(DataComponents.STORED_ENCHANTMENTS);
        AtomicInteger levels = new AtomicInteger();
        enchantments.entrySet().forEach(entry -> {
            levels.addAndGet(enchantments.getLevel(entry.getKey().value()));
        });
        return new MerchantOffer(
                new ItemCost(Items.EMERALD, enchantments.size() * 5 + levels.get() * 3),
                Optional.of(new ItemCost(Items.BOOK)),
                enchantedBook,
                RANDOM.nextInt(1, 4), 1, 1
        );
    }

    public static ItemStack generateStackFromItem(Random random) {
        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);

        // Determine if item should be enchanted and how many times
        int enchantmentChance = random.nextInt(100);
        int enchantments = 1;
        if (enchantmentChance > 90) enchantments = 3;
        else if (enchantmentChance > 75) enchantments = 2;

        // Apply random compatible enchantments (max 20 tries per enchantment (if reached returns the item as is)

        // Avoid duplicated enchantments with set
        Set<String> addedEnchantments = new HashSet<>();

        while (stack.getComponents().get(DataComponents.STORED_ENCHANTMENTS).size() < enchantments) {
            Enchantment enchantment = MerchantUtil.ENCHANTMENTS.get(random.nextInt(MerchantUtil.ENCHANTMENTS.size()));
            if (enchantment.isCurse()) continue;
            if (addedEnchantments.contains(enchantment.getDescriptionId())) continue;
            addedEnchantments.add(enchantment.getDescriptionId());
            stack.enchant(enchantment, RANDOM.nextInt(enchantment.getMaxLevel()));
        }
        addedEnchantments.clear();
        return stack;
    }

}
