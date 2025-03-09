package life.thoms.wandering_traders.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;

public class PotionLootBoxItem extends AbstractLootBoxItem {

    public PotionLootBoxItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public List<Item> generateLootList() {
        return List.of(Items.POTION, Items.SPLASH_POTION);
    }

}
