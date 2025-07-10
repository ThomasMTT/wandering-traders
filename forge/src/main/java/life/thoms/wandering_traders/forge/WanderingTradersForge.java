package life.thoms.wandering_traders.forge;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.forge.config.ConfigHandlerForge;
import life.thoms.wandering_traders.forge.event.EntityEventsForge;
import life.thoms.wandering_traders.forge.loot.LootModifiers;
import life.thoms.wandering_traders.forge.registry.TraderCreativeTabForge;
import life.thoms.wandering_traders.forge.registry.TraderEntitiesForge;
import life.thoms.wandering_traders.forge.registry.TraderItemsForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(WanderingTraders.MOD_ID)
public final class WanderingTradersForge {


    public WanderingTradersForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        WanderingTraders.init();
        TraderEntitiesForge.register(eventBus);
        TraderItemsForge.register(eventBus);
        TraderCreativeTabForge.register(eventBus);
        eventBus.addListener(EventPriority.NORMAL, EntityEventsForge::registerSpawnPlacements);
        eventBus.addListener(EventPriority.NORMAL, EntityEventsForge::registerAttributes);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandlerForge.build(), WanderingTraders.MOD_ID + ".toml");

        LootModifiers.register(eventBus);
    }

}
