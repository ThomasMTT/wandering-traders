package life.thoms.wandering_traders.fabric.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TraderCreativeTabFabric {

    public static final CreativeModeTab CREATIVE_MODE_TABS = FabricItemGroup.builder(ResourceLocation.of("wandering_traders:wandering_traders_tab", ':'))
            .icon(() -> new ItemStack(TraderItemsFabric.END_BELL))
            .title(Component.translatable("creative_tab.wandering_traders_tab"))
            .displayItems(
                    (parameters, output) -> {
                        output.accept(TraderItemsFabric.END_BELL);
                        output.accept(TraderItemsFabric.END_TRADER_SPAWN_EGG);
                        output.accept(TraderItemsFabric.DECORATION_TRADER_SPAWN_EGG);
                        output.accept(TraderItemsFabric.BOOK_TRADER_SPAWN_EGG);
                        output.accept(TraderItemsFabric.FOREST_TRADER_SPAWN_EGG);
                        output.accept(TraderItemsFabric.POTION_TRADER_SPAWN_EGG);
                        output.accept(TraderItemsFabric.ANIMAL_TRADER_SPAWN_EGG);
                        output.accept(TraderItemsFabric.EXCLUSIVE_TRADER_SPAWN_EGG);
                    })
            .build();

    public static void register() {
    }

}
