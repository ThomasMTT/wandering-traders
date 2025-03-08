package life.thoms.wandering_traders.fabric.event;

import life.thoms.wandering_traders.handler.PlayerEventHandler;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

public class PlayerEventsFabric {

    public static void register() {
        ServerLivingEntityEvents.ALLOW_DEATH.register(PlayerEventHandler::onPlayerDeath);
    }

}
