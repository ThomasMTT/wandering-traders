package life.thoms.wandering_traders.forge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.forge.config.ConfigHandlerForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = WanderingTraders.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigEventsForge {

    @SubscribeEvent
    public static void ConfigReload(ModConfigEvent.Loading event) {
        ConfigHandlerForge.updateSharedConfig();
    }

}
