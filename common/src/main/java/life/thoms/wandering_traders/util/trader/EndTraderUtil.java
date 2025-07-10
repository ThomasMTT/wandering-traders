package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.server.data.LostLootData;
import life.thoms.wandering_traders.util.LostLootUtil;
import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.*;

public class EndTraderUtil {

    public static final List<Item> PRICE_ITEMS = List.of(
            Items.PHANTOM_MEMBRANE,
            Items.SPIDER_EYE,
            Items.BLAZE_ROD,
            Items.RABBIT_FOOT,
            Items.SCUTE
    );

    private static final Random RANDOM = new Random();

    public static MerchantOffer createOffer(ItemStack stack) {
        ItemStack firstCost = new ItemStack(PRICE_ITEMS.get(RANDOM.nextInt(PRICE_ITEMS.size())),
                RANDOM.nextInt(5, 20));
        Optional<ItemStack> secondCost = Optional.empty();

        if (RANDOM.nextBoolean()) {
            Item costItem;
            do {
                costItem = PRICE_ITEMS.get(RANDOM.nextInt(PRICE_ITEMS.size()));
            } while (costItem == firstCost.getItem());

            secondCost = Optional.of(new ItemStack(costItem, RANDOM.nextInt(1, 21 - firstCost.getCount())));
        }
        return new MerchantOffer(firstCost, secondCost.orElse(new ItemStack(Items.AIR)), stack, 0, 1, 1, 1);
    }

    public static MerchantOffers createOffersFromLostLoot(UUID playerUUID, MerchantOffers existingOffers) {
        List<ItemStack> playerLostLoot = LostLootUtil.getPlayerLoot(playerUUID);
        if (playerLostLoot == null || playerLostLoot.isEmpty()) {
            return existingOffers;
        }

        for (ItemStack stack : playerLostLoot) {
            if (stack.getItem() != Items.AIR) {
                MerchantOffer offer = createOffer(stack);
                existingOffers.add(offer);
            }
        }

        MerchantUtil.sortOffers(existingOffers);
        LostLootUtil.clearPlayerLoot(playerUUID);
        LostLootData.INSTANCE.setDirty();
        return existingOffers;
    }

}
