package life.thoms.wandering_traders.forge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.handler.PlayerEventHandler;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WanderingTraders.MOD_ID)
public class PlayerEventsForge {

    @SubscribeEvent
    public static void onEntityLeaveLevel(LivingDeathEvent event) {
        PlayerEventHandler.onPlayerDeath(event.getEntity(), event.getSource(), 0);
    }

}
