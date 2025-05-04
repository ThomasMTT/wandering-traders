package life.thoms.wandering_traders.neoforge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.neoforge.config.ConfigHandlerNeoForge;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;

@EventBusSubscriber(modid = WanderingTraders.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ConfigEvents {

    @SubscribeEvent
    public static void ConfigReload(ModConfigEvent.Loading event) {

        ConfigHandlerNeoForge.updateSharedConfig();

    }

}
