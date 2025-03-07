package life.thoms.wandering_traders.fabric;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.EndTraderEntity;
import life.thoms.wandering_traders.fabric.registry.TraderEntitiesFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public final class WanderingTradersFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        WanderingTraders.init();
        FabricDefaultAttributeRegistry.register(TraderEntitiesFabric.END_TRADER, EndTraderEntity.createAttributes());

    }

}
