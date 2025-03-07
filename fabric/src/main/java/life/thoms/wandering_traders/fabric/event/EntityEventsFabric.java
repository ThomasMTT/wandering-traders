package life.thoms.wandering_traders.fabric.event;

import life.thoms.wandering_traders.handler.ServerEventHandler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class EntityEventsFabric {

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(ServerEventHandler::onServerStarted);
    }

}
