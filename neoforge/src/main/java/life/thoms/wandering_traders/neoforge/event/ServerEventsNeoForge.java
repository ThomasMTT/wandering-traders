package life.thoms.wandering_traders.neoforge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.handler.ServerEventHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

@EventBusSubscriber(modid = WanderingTraders.MOD_ID)
public class ServerEventsNeoForge {

    @SubscribeEvent
    private static void onEntityLeaveLevel(ServerStartedEvent event) {
        ServerEventHandler.onServerStarted(event.getServer());
    }

}
