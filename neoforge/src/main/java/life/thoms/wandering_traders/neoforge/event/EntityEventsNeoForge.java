package life.thoms.wandering_traders.neoforge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.handler.EntityEventHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;

@EventBusSubscriber(modid = WanderingTraders.MOD_ID)
public class EntityEventsNeoForge {

    @SubscribeEvent
    private static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            EntityEventHandler.onEntityLeaveLevel(event.getEntity(), event.getLevel());
        }
    }

}
