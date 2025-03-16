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

    public static final Random RANDOM = new Random();

    public static MerchantOffers generateOffers() {
        MerchantOffers offers = new MerchantOffers();
        offers.clear();

        for (int i = 0; i < 12; i++) {

            offers.add(MerchantUtil.createOffer(generatePotion(), RANDOM.nextInt(8,15),
                    RANDOM.nextInt(1,4)));
        }
        offers.sort((x, z) -> Integer.compare(z.getItemCostA().count(), x.getItemCostA().count()));
        return offers;
    }

    public static ItemStack generatePotion() {

        // Used to take vanilla random potion and its default duration
        Potion randomPotion;
        do {
            randomPotion = MerchantUtil.POTIONS.get(RANDOM.nextInt(MerchantUtil.POTIONS.size()));
        } while (randomPotion.getEffects().isEmpty() || randomPotion.hasInstantEffects());

        MobEffectInstance randomEffectInstance = randomPotion.getEffects().getFirst();
        ItemStack potionStack = new ItemStack(RANDOM.nextBoolean() ? Items.POTION : Items.SPLASH_POTION);

        // Create new potion
        MobEffectInstance effectInstance = new MobEffectInstance(randomEffectInstance.getEffect(), (int) (randomEffectInstance.getDuration() * 1.5));
        PotionContents potionContents = potionStack.getComponents().get(DataComponents.POTION_CONTENTS);
        PotionContents contents = potionContents.withEffectAdded(effectInstance);

        potionStack.applyComponents(DataComponentPatch.builder().set(DataComponents.POTION_CONTENTS, contents).build());
        return potionStack;
    }

}
