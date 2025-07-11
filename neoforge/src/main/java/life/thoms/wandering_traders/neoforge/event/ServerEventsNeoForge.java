package life.thoms.wandering_traders.neoforge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.handler.ServerEventHandler;
import life.thoms.wandering_traders.neoforge.registry.TraderEntitiesNeoForge;
import life.thoms.wandering_traders.neoforge.registry.TraderItemsNeoForge;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@EventBusSubscriber(modid = WanderingTraders.MOD_ID)
public class ServerEventsNeoForge {

    @SubscribeEvent
    private static void onServerStarted(ServerStartingEvent event) {
        TraderItemsNeoForge.registerCommon();
        TraderEntitiesNeoForge.registerCommon();
        ServerEventHandler.onServerStarted(event.getServer());
    }

}
