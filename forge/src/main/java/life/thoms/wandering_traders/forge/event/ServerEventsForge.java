package life.thoms.wandering_traders.forge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.forge.registry.TraderEntitiesForge;
import life.thoms.wandering_traders.forge.registry.TraderItemsForge;
import life.thoms.wandering_traders.handler.ServerEventHandler;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WanderingTraders.MOD_ID)
public class ServerEventsForge {

    @SubscribeEvent
    public static void onServerStarting(ServerStartedEvent event) {
        ServerEventHandler.onServerStarted(event.getServer());
    }

    @SubscribeEvent
    public static void onServerStarting(ServerAboutToStartEvent event) {
        TraderItemsForge.registerCommon();
        TraderEntitiesForge.registerCommon();
    }

}
