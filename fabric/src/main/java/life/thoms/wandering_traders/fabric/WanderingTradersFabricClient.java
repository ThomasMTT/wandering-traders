package life.thoms.wandering_traders.fabric;

import life.thoms.wandering_traders.fabric.registry.TraderEntitiesFabric;
import life.thoms.wandering_traders.rendering.EndTraderRenderer;
import life.thoms.wandering_traders.rendering.BookTraderRenderer;
import life.thoms.wandering_traders.rendering.ForestTraderRenderer;
import life.thoms.wandering_traders.rendering.GamblingTraderRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public final class WanderingTradersFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(TraderEntitiesFabric.END_TRADER, EndTraderRenderer::new);
        EntityRendererRegistry.register(TraderEntitiesFabric.GAMBLING_TRADER, GamblingTraderRenderer::new);
        EntityRendererRegistry.register(TraderEntitiesFabric.BOOK_TRADER, BookTraderRenderer::new);
        EntityRendererRegistry.register(TraderEntitiesFabric.FOREST_TRADER, ForestTraderRenderer::new);
    }

}
