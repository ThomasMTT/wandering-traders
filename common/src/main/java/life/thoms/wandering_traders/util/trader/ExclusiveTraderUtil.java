package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.*;

public class ExclusiveTraderUtil {

    public static final Random RANDOM = new Random();



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
        ItemStack stack = new ItemStack(MerchantUtil.EXCLUSIVE_ITEMS_LIST.get(
                RANDOM.nextInt(MerchantUtil.EXCLUSIVE_ITEMS_LIST.size())));
        int price = MerchantUtil.EXCLUSIVE_ITEMS.get(stack.getItem());

        return new MerchantOffer(
                new ItemCost(Items.EMERALD, price),
                stack,
                1, 1, 1
        );
    }

}
