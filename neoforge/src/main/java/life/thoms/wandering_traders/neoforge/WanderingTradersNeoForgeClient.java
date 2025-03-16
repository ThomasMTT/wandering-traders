package life.thoms.wandering_traders.neoforge;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.model.PotionTraderModel;
import life.thoms.wandering_traders.neoforge.registry.TraderEntitiesNeoForge;
import life.thoms.wandering_traders.rendering.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = WanderingTraders.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WanderingTradersNeoForgeClient {

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TraderEntitiesNeoForge.END_TRADER.get(), EndTraderRenderer::new);
        event.registerEntityRenderer(TraderEntitiesNeoForge.GAMBLING_TRADER.get(), GamblingTraderRenderer::new);
        event.registerEntityRenderer(TraderEntitiesNeoForge.BOOK_TRADER.get(), BookTraderRenderer::new);
        event.registerEntityRenderer(TraderEntitiesNeoForge.FOREST_TRADER.get(), ForestTraderRenderer::new);
        event.registerEntityRenderer(TraderEntitiesNeoForge.POTION_TRADER.get(), PotionTraderRenderer::new);
        event.registerEntityRenderer(TraderEntitiesNeoForge.ANIMAL_TRADER.get(), AnimalTraderRenderer::new);
        event.registerEntityRenderer(TraderEntitiesNeoForge.EXCLUSIVE_TRADER.get(), ExclusiveTraderRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PotionTraderModel.LAYER_LOCATION, PotionTraderModel::createBodyLayer);
    }

}
