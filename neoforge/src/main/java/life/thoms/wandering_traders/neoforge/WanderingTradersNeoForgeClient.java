package life.thoms.wandering_traders.neoforge;

import life.thoms.wandering_traders.WanderingTraders;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = WanderingTraders.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WanderingTradersNeoForgeClient {

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {

    }

}
