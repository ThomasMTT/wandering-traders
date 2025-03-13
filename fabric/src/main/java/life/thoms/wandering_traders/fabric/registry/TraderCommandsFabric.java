package life.thoms.wandering_traders.fabric.registry;

import life.thoms.wandering_traders.command.LostLootCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class TraderCommandsFabric {

    public static void register() {
        CommandRegistrationCallback.EVENT.register(LostLootCommand::register);
    }

}
