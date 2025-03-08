package life.thoms.wandering_traders.neoforge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.handler.PlayerEventHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = WanderingTraders.MOD_ID)
public class PlayerEventsNeoForge {

    @SubscribeEvent
    private static void onEntityLeaveLevel(LivingDeathEvent event) {
        PlayerEventHandler.onPlayerDeath(event.getEntity(), event.getSource(), 0);
    }

}
