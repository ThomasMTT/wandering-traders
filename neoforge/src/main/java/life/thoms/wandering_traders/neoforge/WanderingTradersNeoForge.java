package life.thoms.wandering_traders.neoforge;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.EndTraderEntity;
import life.thoms.wandering_traders.neoforge.registry.TraderCreativeTabNeoForge;
import life.thoms.wandering_traders.neoforge.registry.TraderEntitiesNeoForge;
import life.thoms.wandering_traders.neoforge.registry.TraderItemsNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod(WanderingTraders.MOD_ID)
public final class WanderingTradersNeoForge {

    public WanderingTradersNeoForge(IEventBus eventBus) {
        WanderingTraders.init();
        TraderEntitiesNeoForge.register(eventBus);
        TraderItemsNeoForge.register(eventBus);
        TraderCreativeTabNeoForge.register(eventBus);
        eventBus.register(this);
    }

    @SubscribeEvent
    public void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(TraderEntitiesNeoForge.END_TRADER.get(), EndTraderEntity.createAttributes().build());
    }

}
