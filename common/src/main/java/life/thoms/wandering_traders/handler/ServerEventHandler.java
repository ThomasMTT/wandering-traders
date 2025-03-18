package life.thoms.wandering_traders.handler;

import life.thoms.wandering_traders.config.ModConfigs;
import life.thoms.wandering_traders.server.data.LostLootData;
import life.thoms.wandering_traders.server.data.PlayerEndTraderData;
import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.server.MinecraftServer;

public class ServerEventHandler {

    public static void onServerStarted(MinecraftServer server) {
        if (ModConfigs.ENABLE_LOOT_RECOVERY) {
            PlayerEndTraderData.createServerState(server);
            LostLootData.createServerState(server);
        }

        MerchantUtil.register(server.overworld());
    }

}
