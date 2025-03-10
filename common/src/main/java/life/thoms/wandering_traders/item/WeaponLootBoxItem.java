package life.thoms.wandering_traders.item;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.Item;

import java.util.List;

public class WeaponLootBoxItem extends AbstractLootBoxItem {

    public WeaponLootBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    public List<Item> generateLootList() {
        return MerchantUtil.WEAPON_ITEMS;
    }

}
