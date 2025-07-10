package life.thoms.wandering_traders.fabric;

import life.thoms.wandering_traders.fabric.registry.TraderEntitiesFabric;
import life.thoms.wandering_traders.model.PotionTraderModel;
import life.thoms.wandering_traders.rendering.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class WanderingTradersFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // Renderers
        EntityRendererRegistry.register(TraderEntitiesFabric.END_TRADER, EndTraderRenderer::new);
        EntityRendererRegistry.register(TraderEntitiesFabric.DECORATION_TRADER, DecorationTraderRenderer::new);
        EntityRendererRegistry.register(TraderEntitiesFabric.BOOK_TRADER, BookTraderRenderer::new);
        EntityRendererRegistry.register(TraderEntitiesFabric.FOREST_TRADER, ForestTraderRenderer::new);
        EntityRendererRegistry.register(TraderEntitiesFabric.POTION_TRADER, PotionTraderRenderer::new);
        EntityRendererRegistry.register(TraderEntitiesFabric.ANIMAL_TRADER, AnimalTraderRenderer::new);
        EntityRendererRegistry.register(TraderEntitiesFabric.EXCLUSIVE_TRADER, ExclusiveTraderRenderer::new);

        // Custom Models
        EntityModelLayerRegistry.registerModelLayer(PotionTraderModel.LAYER_LOCATION, PotionTraderModel::createBodyLayer);
    }

}
