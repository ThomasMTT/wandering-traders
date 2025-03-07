package life.thoms.wandering_traders.fabric.event;

import life.thoms.wandering_traders.handler.EntityEventHandler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

public class ServerEventsFabric {

    public static void register() {
        ServerEntityEvents.ENTITY_UNLOAD.register(EntityEventHandler::onEntityLeaveLevel);
    }

}
