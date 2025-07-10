package life.thoms.wandering_traders.forge.registry;

import life.thoms.wandering_traders.WanderingTraders;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TraderCreativeTabForge {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WanderingTraders.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TRADERS_TAB = CREATIVE_MODE_TABS.register("wandering_traders_tab",
            () -> CreativeModeTab.builder().icon(() ->
                            new ItemStack(TraderItemsForge.END_BELL.get()))
                    .title(Component.translatable("creative_tab.wandering_traders_tab")).displayItems(
                            (parameters, output) -> {
                                output.accept(TraderItemsForge.END_BELL.get());
                                output.accept(TraderItemsForge.END_TRADER_SPAWN_EGG.get());
                                output.accept(TraderItemsForge.DECORATION_TRADER_SPAWN_EGG.get());
                                output.accept(TraderItemsForge.BOOK_TRADER_SPAWN_EGG.get());
                                output.accept(TraderItemsForge.FOREST_TRADER_SPAWN_EGG.get());
                                output.accept(TraderItemsForge.POTION_TRADER_SPAWN_EGG.get());
                                output.accept(TraderItemsForge.ANIMAL_TRADER_SPAWN_EGG.get());
                                output.accept(TraderItemsForge.EXCLUSIVE_TRADER_SPAWN_EGG.get());
                            })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }



}
