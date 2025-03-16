package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.trading.MerchantOffers;

import java.util.Random;

public class PotionTraderUtil {

    private static final Random RANDOM = new Random();
    private static final int NUMBER_OF_OFFERS = 12;
    private static final int MIN_COST = 8;
    private static final int MAX_COST = 15;
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 4;
    private static final int MAX_ATTEMPTS = 10;

    public static MerchantOffers generateOffers() {
        MerchantOffers offers = new MerchantOffers();
        offers.clear();

        for (int i = 0; i < NUMBER_OF_OFFERS; i++) {
            offers.add(MerchantUtil.createOffer(generatePotion(), RANDOM.nextInt(MIN_COST, MAX_COST),
                    RANDOM.nextInt(MIN_QUANTITY, MAX_QUANTITY)));
        }
        MerchantUtil.sortOffers(offers);
        return offers;
    }

    public static ItemStack generatePotion() {
        Potion randomPotion = null;
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            randomPotion = MerchantUtil.POTIONS.get(RANDOM.nextInt(MerchantUtil.POTIONS.size()));
            if (!randomPotion.getEffects().isEmpty() && !randomPotion.hasInstantEffects()) {
                break;
            }
            attempts++;
        }

        MobEffectInstance randomEffectInstance = randomPotion.getEffects().getFirst();
        ItemStack potionStack = new ItemStack(RANDOM.nextBoolean() ? Items.POTION : Items.SPLASH_POTION);
        MobEffectInstance effectInstance = new MobEffectInstance(randomEffectInstance.getEffect(), (int) (randomEffectInstance.getDuration() * 1.5));
        PotionContents potionContents = potionStack.getComponents().get(DataComponents.POTION_CONTENTS);
        PotionContents contents = potionContents.withEffectAdded(effectInstance);

        potionStack.applyComponents(DataComponentPatch.builder().set(DataComponents.POTION_CONTENTS, contents).build());
        return potionStack;
    }
}