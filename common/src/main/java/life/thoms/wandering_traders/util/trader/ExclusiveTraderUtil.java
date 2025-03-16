package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.*;

public class ExclusiveTraderUtil {

    public static final Random RANDOM = new Random();
    private static final int MAX_USES = 1;

    public static MerchantOffers generateOffers() {
        MerchantOffers offers = new MerchantOffers();
        offers.clear();

        for (int i = 0; i < 6; i++) {
            ItemStack stack = new ItemStack(MerchantUtil.EXCLUSIVE_ITEMS_LIST.get(
                    RANDOM.nextInt(MerchantUtil.EXCLUSIVE_ITEMS_LIST.size())));

            int price = MerchantUtil.EXCLUSIVE_ITEMS.get(stack.getItem());
            offers.add(MerchantUtil.createOffer(stack, price, MAX_USES));
        }
        MerchantUtil.sortOffers(offers);
        return offers;
    }

}
