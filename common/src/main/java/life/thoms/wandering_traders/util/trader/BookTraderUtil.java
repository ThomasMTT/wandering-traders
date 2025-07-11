package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BookTraderUtil {

    public static final Random RANDOM = new Random();

    private static final Map<Enchantment, Integer> ENCHANTMENT_CACHE = new HashMap<>();

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
        Map<Enchantment,Integer> enchantmentList = EnchantmentHelper.getEnchantments(enchantedBook);
        AtomicInteger levels = new AtomicInteger();

        for (Enchantment enchantment: enchantmentList.keySet()) {
            Integer addLevels = enchantmentList.get(enchantment);
            levels.addAndGet(addLevels);
        }

        return new MerchantOffer(
                new ItemStack(Items.EMERALD, enchantmentList.size() * 5 + levels.get() * 3),
                new ItemStack(Items.BOOK),
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
        int i = 0;
        while (EnchantmentHelper.getEnchantments(stack).size() < enchantments && i < 20) {
            Enchantment enchantment = MerchantUtil.ENCHANTMENTS.get(random.nextInt(MerchantUtil.ENCHANTMENTS.size()));
            // Enchant if not a curse, else try another enchantment
            if (enchantment.isCurse()) continue;
            if (EnchantmentHelper.getEnchantments(stack).containsKey(enchantment)) continue;
            ENCHANTMENT_CACHE.put(enchantment, Math.max(1, RANDOM.nextInt(enchantment.getMaxLevel())));
            EnchantmentHelper.setEnchantments(ENCHANTMENT_CACHE, stack);
            ENCHANTMENT_CACHE.clear();
            i+=1;
        }
        return stack;
    }

}
