package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.server.ModRegistryAccess;
import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.*;

public class ExclusiveTraderUtil {

    public static final Random RANDOM = new Random();

    public static Map<Item, Integer> EXCLUSIVE_ITEMS;
    public static List<Item> EXCLUSIVE_ITEMS_LIST;

    public static void prepareExclusiveItems() {
        EXCLUSIVE_ITEMS = new HashMap<>() {{
            put(ModRegistryAccess.ITEM_ACCESS.get("end_bell"), 16);
            put(Items.TOTEM_OF_UNDYING, 32);
            put(Items.ENCHANTED_GOLDEN_APPLE, 20);
            put(Items.WITHER_SKELETON_SKULL, 48);
            put(Items.NAME_TAG, 12);
            put(Items.SADDLE, 8);
            put(Items.DIAMOND_HORSE_ARMOR, 12);
        }};
        EXCLUSIVE_ITEMS_LIST = EXCLUSIVE_ITEMS.keySet().stream().toList();
    }

    public static MerchantOffers generateOffers() {
        MerchantOffers offers = new MerchantOffers();
        offers.clear();

        for (int i = 0; i < 6; i++) {
            offers.add(createOffer());
        }
        offers.sort((x, z) -> Integer.compare(z.getItemCostA().count(), x.getItemCostA().count()));
        return offers;
    }

    private static MerchantOffer createOffer() {
        ItemStack stack = new ItemStack(EXCLUSIVE_ITEMS_LIST.get(RANDOM.nextInt(EXCLUSIVE_ITEMS_LIST.size())));
        int price = EXCLUSIVE_ITEMS.get(stack.getItem());

        return new MerchantOffer(
                new ItemCost(Items.EMERALD, price),
                stack,
                1, 1, 1
        );
    }

}
