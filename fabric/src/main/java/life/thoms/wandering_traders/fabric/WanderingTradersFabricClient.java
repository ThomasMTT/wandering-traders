package life.thoms.wandering_traders.fabric;

import life.thoms.wandering_traders.fabric.registry.TraderEntitiesFabric;
import life.thoms.wandering_traders.rendering.EndTraderRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public final class WanderingTradersFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(TraderEntitiesFabric.END_TRADER, EndTraderRenderer::new);
    }

}
