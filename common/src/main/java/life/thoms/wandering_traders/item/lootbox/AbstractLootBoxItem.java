package life.thoms.wandering_traders.item.lootbox;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public abstract class AbstractLootBoxItem extends Item {

    public static final Random RANDOM = new Random();
    public final List<Item> lootList = generateLootList();

    public AbstractLootBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack lootBoxItem = player.getItemInHand(usedHand);
        ItemStack rewardItem = generateReward();

        if (player.getInventory().getFreeSlot() > -1) {
            if (!level.isClientSide()) {
                player.addItem(rewardItem);
                if (!player.isCreative()) {
                    lootBoxItem.setCount(lootBoxItem.getCount() - 1);
                }
            }
            player.playSound(SoundEvents.ITEM_PICKUP);
        }
        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    public ItemStack generateReward() {
        Item randomItem = lootList.get(RANDOM.nextInt(lootList.size()));

        // If netherite 50/50 chance of changing to another item
        if (randomItem instanceof TieredItem tieredItem) {
            if (tieredItem.getTier().equals(Tiers.NETHERITE)) {
                if (RANDOM.nextBoolean()) {
                    randomItem = lootList.get(RANDOM.nextInt(lootList.size()));
                }
            }

        }
        if (randomItem instanceof ArmorItem armorItem) {
            if (armorItem.getMaterial().equals(ArmorMaterials.NETHERITE)) {
                if (RANDOM.nextBoolean()) {
                    randomItem = lootList.get(RANDOM.nextInt(lootList.size()));
                }
            }
        }
        return MerchantUtil.generateStackFromItem(RANDOM, randomItem);
    }

    public abstract List<Item> generateLootList();

}
