package life.thoms.wandering_traders.item.lootbox;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.Item;

import java.util.List;

public class ArmorLootBoxItem extends AbstractLootBoxItem {

    public ArmorLootBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    public List<Item> generateLootList() {
        return MerchantUtil.ARMOR_ITEMS;
    }

}
