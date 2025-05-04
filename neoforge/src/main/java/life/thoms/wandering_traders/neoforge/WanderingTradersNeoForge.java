package life.thoms.wandering_traders.neoforge;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.neoforge.config.ConfigHandlerNeoForge;
import life.thoms.wandering_traders.neoforge.event.EntityEventsNeoForge;
import life.thoms.wandering_traders.neoforge.loot.LootModifiers;
import life.thoms.wandering_traders.neoforge.registry.TraderCreativeTabNeoForge;
import life.thoms.wandering_traders.neoforge.registry.TraderEntitiesNeoForge;
import life.thoms.wandering_traders.neoforge.registry.TraderItemsNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;

@Mod(WanderingTraders.MOD_ID)
public final class WanderingTradersNeoForge {

    public WanderingTradersNeoForge(IEventBus eventBus, ModContainer modContainer) {
        WanderingTraders.init();
        TraderEntitiesNeoForge.register(eventBus);
        TraderItemsNeoForge.register(eventBus);
        TraderCreativeTabNeoForge.register(eventBus);
        eventBus.addListener(SpawnPlacementRegisterEvent.class, EntityEventsNeoForge::registerSpawnPlacements);
        eventBus.addListener(EntityAttributeCreationEvent.class, EntityEventsNeoForge::registerAttributes);

        modContainer.registerConfig(ModConfig.Type.COMMON, ConfigHandlerNeoForge.build(), "wandering_traders.toml");

        LootModifiers.register(eventBus);
    }


}
