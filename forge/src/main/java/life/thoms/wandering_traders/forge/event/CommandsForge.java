package life.thoms.wandering_traders.forge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.command.LostLootCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = WanderingTraders.MOD_ID)
public class CommandsForge {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        LostLootCommand.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
    }


}
