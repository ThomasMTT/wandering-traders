package life.thoms.wandering_traders.neoforge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.command.LostLootCommand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = WanderingTraders.MOD_ID)
public class CommandsNeoForge {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        LostLootCommand.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
    }


}
