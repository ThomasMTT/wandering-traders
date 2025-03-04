package life.thoms.wandering_traders.fabric;

import life.thoms.wandering_traders.WanderingTraders;
import net.fabricmc.api.ModInitializer;

public final class WanderingTradersFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        WanderingTraders.init();
    }

}
