package life.thoms.wandering_traders.fabric;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.fabric.event.EntityEventsFabric;
import life.thoms.wandering_traders.fabric.event.PlayerEventsFabric;
import life.thoms.wandering_traders.fabric.event.ServerEventsFabric;
import life.thoms.wandering_traders.fabric.registry.TraderCreativeTabFabric;
import life.thoms.wandering_traders.fabric.registry.TraderEntitiesFabric;
import life.thoms.wandering_traders.fabric.registry.TraderItemsFabric;
import life.thoms.wandering_traders.fabric.world.gen.TraderEntitySpawner;
import net.fabricmc.api.ModInitializer;

public final class WanderingTradersFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        WanderingTraders.init();
        EntityEventsFabric.register();
        TraderEntitiesFabric.register();
        TraderItemsFabric.register();
        TraderCreativeTabFabric.register();
        ServerEventsFabric.register();
        PlayerEventsFabric.register();
        TraderEntitySpawner.register();
    }

}
