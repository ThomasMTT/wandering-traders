package life.thoms.wandering_traders.forge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.forge.registry.TraderItemsForge;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = WanderingTraders.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabEventForge {

    static Supplier<ItemStack> iconSupplier = () -> new ItemStack(TraderItemsForge.END_BELL.get());

    public static Consumer<CreativeModeTab.Builder> builder = builder ->

            builder.icon(iconSupplier)
                    .title(Component.translatable("creative_tab.wandering_traders_tab"))
                    .displayItems((params, output) -> {
                            output.accept(TraderItemsForge.END_BELL.get());
                            output.accept(TraderItemsForge.END_TRADER_SPAWN_EGG.get());
                            output.accept(TraderItemsForge.DECORATION_TRADER_SPAWN_EGG.get());
                            output.accept(TraderItemsForge.BOOK_TRADER_SPAWN_EGG.get());
                            output.accept(TraderItemsForge.FOREST_TRADER_SPAWN_EGG.get());
                            output.accept(TraderItemsForge.POTION_TRADER_SPAWN_EGG.get());
                            output.accept(TraderItemsForge.ANIMAL_TRADER_SPAWN_EGG.get());
                            output.accept(TraderItemsForge.EXCLUSIVE_TRADER_SPAWN_EGG.get());
                    }

);

    @SubscribeEvent
    public static void registerCreativeTab(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(ResourceLocation.bySeparator("wandering_traders:wandering_traders_tab", ':'), builder);

    }
}
