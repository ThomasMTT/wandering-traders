package life.thoms.wandering_traders.forge;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.forge.registry.TraderEntitiesForge;
import life.thoms.wandering_traders.model.PotionTraderModel;
import life.thoms.wandering_traders.rendering.*;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WanderingTraders.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WanderingTradersForgeClient {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        EntityRenderers.register(TraderEntitiesForge.END_TRADER.get(), EndTraderRenderer::new);
        EntityRenderers.register(TraderEntitiesForge.DECORATION_TRADER.get(), DecorationTraderRenderer::new);
        EntityRenderers.register(TraderEntitiesForge.BOOK_TRADER.get(), BookTraderRenderer::new);
        EntityRenderers.register(TraderEntitiesForge.FOREST_TRADER.get(), ForestTraderRenderer::new);
        EntityRenderers.register(TraderEntitiesForge.POTION_TRADER.get(), PotionTraderRenderer::new);
        EntityRenderers.register(TraderEntitiesForge.ANIMAL_TRADER.get(), AnimalTraderRenderer::new);
        EntityRenderers.register(TraderEntitiesForge.EXCLUSIVE_TRADER.get(), ExclusiveTraderRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PotionTraderModel.LAYER_LOCATION, PotionTraderModel::createBodyLayer);
    }

}
