package life.thoms.wandering_traders.fabric.registry;

import life.thoms.wandering_traders.WanderingTraders;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class TraderItemsFabric {

    public static final Item END_TRADER_SPAWN_EGG = register("end_trader_spawn_egg",
            new SpawnEggItem(TraderEntitiesFabric.END_TRADER, 0x5e0da5, 0xcea512, new Item.Properties()));

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(WanderingTraders.MOD_ID + ":" + name), item);
    }

    public static void register() {
    }

}
