package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffers;

public class DecorationTraderUtil {

    private static final int MAX_USES = 64;

    public static MerchantOffers generateOffers() {
        MerchantOffers offers = new MerchantOffers();
        offers.clear();

        for (Item item : MerchantUtil.DECORATIVE_ITEMS_LIST) {
            ItemStack stack = new ItemStack(item);

            int price = MerchantUtil.DECORATIVE_ITEMS.get(item);
            offers.add(MerchantUtil.createOffer(stack, price, MAX_USES));
        }
        return offers;
    }

}
