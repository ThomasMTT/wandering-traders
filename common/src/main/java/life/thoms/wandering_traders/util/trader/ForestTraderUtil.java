package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.*;

public class ForestTraderUtil {

    private static final List<Item> FOREST_SEED_LIST = new ArrayList<>(Arrays.asList(
            Items.POTATO,
            Items.CARROT,
            Items.WHEAT_SEEDS,
            Items.MELON_SEEDS,
            Items.PUMPKIN_SEEDS,
            Items.BEETROOT_SEEDS,
            Items.BROWN_MUSHROOM,
            Items.RED_MUSHROOM,
            Items.CRIMSON_FUNGUS,
            Items.WARPED_FUNGUS
    ));

    public static MerchantOffers generateOffers() {
        MerchantOffers offers = new MerchantOffers();
        offers.clear();
        FOREST_SEED_LIST.forEach(item -> {
            offers.add(new MerchantOffer(
                    new ItemCost(Items.EMERALD, item.getDescriptionId().contains("fungus") ? 8 : 4),
                    new ItemStack(item, 16),
                    1, 8, 1
            ));
        });

        MerchantUtil.SAPPLING_ITEMS.forEach(item -> {
            offers.add(new MerchantOffer(
                    new ItemCost(Items.EMERALD, 8),
                    new ItemStack(item, 16),
                    3, 1, 1
            ));
        });

        MerchantUtil.sortOffers(offers);

        return offers;
    }

}
