package life.thoms.wandering_traders.neoforge.registry;

import life.thoms.wandering_traders.WanderingTraders;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TraderItemsNeoForge {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            BuiltInRegistries.ITEM,
            WanderingTraders.MOD_ID
    );

    public static final DeferredHolder<Item, DeferredSpawnEggItem> END_TRADER_SPAWN_EGG = ITEMS.register(
            "end_trader_spawn_egg",
            () -> new DeferredSpawnEggItem(TraderEntitiesNeoForge.END_TRADER,
                    0x5e0da5, 0xcea512, new Item.Properties())
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
