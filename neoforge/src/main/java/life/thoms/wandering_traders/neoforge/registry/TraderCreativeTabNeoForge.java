package life.thoms.wandering_traders.neoforge.registry;

import life.thoms.wandering_traders.WanderingTraders;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TraderCreativeTabNeoForge {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WanderingTraders.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TRADERS_TAB = CREATIVE_MODE_TABS.register("wandering_traders_tab",
            () -> CreativeModeTab.builder().icon(() ->
                            new ItemStack(TraderItemsNeoForge.END_TRADER_SPAWN_EGG.get()))
                    .title(Component.translatable("creative_tab.wandering_traders_tab")).displayItems(
                            (parameters, output) -> {
                                output.accept(TraderItemsNeoForge.END_TRADER_SPAWN_EGG.get());
                                output.accept(TraderItemsNeoForge.GAMBLING_TRADER_SPAWN_EGG.get());
                            })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
