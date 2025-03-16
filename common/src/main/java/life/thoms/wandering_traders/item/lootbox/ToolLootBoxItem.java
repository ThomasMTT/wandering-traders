package life.thoms.wandering_traders.item.lootbox;

import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.world.item.Item;

import java.util.List;

public class ToolLootBoxItem extends AbstractLootBoxItem {

    public ToolLootBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    public List<Item> generateLootList() {
        return MerchantUtil.TOOL_ITEMS;
    }

}
