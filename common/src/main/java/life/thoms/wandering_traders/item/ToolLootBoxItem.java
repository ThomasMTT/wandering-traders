package life.thoms.wandering_traders.item;

import life.thoms.wandering_traders.util.LootBoxUtil;
import net.minecraft.world.item.Item;

import java.util.List;

public class ToolLootBoxItem extends AbstractLootBoxItem {

    public ToolLootBoxItem(Properties properties) {
        super(properties);
    }

    @Override
    public List<Item> generateLootList() {
        return LootBoxUtil.TOOL_ITEMS;
    }

}
