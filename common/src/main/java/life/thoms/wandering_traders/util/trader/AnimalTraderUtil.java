package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.Random;

public class AnimalTraderUtil {

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
        ItemStack stack = new ItemStack(MerchantUtil.ANIMAL_SPAWN_EGG_ITEMS
                .get(RANDOM.nextInt(MerchantUtil.ANIMAL_SPAWN_EGG_ITEMS.size())));
        return new MerchantOffer(
                new ItemCost(Items.EMERALD, RANDOM.nextInt(6,10)),
                        stack,
                RANDOM.nextInt(2, 4), 1, 1
        );
    }


}
