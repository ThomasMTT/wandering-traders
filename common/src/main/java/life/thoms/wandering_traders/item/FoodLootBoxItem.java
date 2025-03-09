package life.thoms.wandering_traders.item;

import life.thoms.wandering_traders.util.LootBoxUtil;
import net.minecraft.world.item.Item;

import java.util.List;

public class FoodLootBoxItem extends AbstractLootBoxItem {

    public FoodLootBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    public List<Item> generateLootList() {
        return LootBoxUtil.FOOD_ITEMS;
    }

}
