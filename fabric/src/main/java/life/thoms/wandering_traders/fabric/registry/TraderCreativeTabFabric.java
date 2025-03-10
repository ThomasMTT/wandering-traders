package life.thoms.wandering_traders.fabric.registry;

import life.thoms.wandering_traders.WanderingTraders;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TraderCreativeTabFabric {

    public static final CreativeModeTab CREATIVE_MODE_TABS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TraderItemsFabric.END_TRADER_SPAWN_EGG))
            .title(Component.translatable("creative_tab.wandering_traders_tab"))
            .displayItems(
                    (parameters, output) -> {
                        output.accept(TraderItemsFabric.END_TRADER_SPAWN_EGG);
                        output.accept(TraderItemsFabric.GAMBLING_TRADER_SPAWN_EGG);
                        output.accept(TraderItemsFabric.BOOK_TRADER_SPAWN_EGG);
                        output.accept(TraderItemsFabric.FOREST_TRADER_SPAWN_EGG);
                        output.accept(TraderItemsFabric.ARMOR_LOOT_BOX);
                        output.accept(TraderItemsFabric.WEAPON_LOOT_BOX);
                        output.accept(TraderItemsFabric.TOOL_LOOT_BOX);
                        output.accept(TraderItemsFabric.FOOD_LOOT_BOX);
                        output.accept(TraderItemsFabric.POTION_LOOT_BOX);
                    })
            .build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                new ResourceLocation(WanderingTraders.MOD_ID + ":wandering_traders_tab"), CREATIVE_MODE_TABS);
    }

}
