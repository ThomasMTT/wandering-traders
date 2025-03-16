package life.thoms.wandering_traders.handler;

import life.thoms.wandering_traders.server.data.LostLootData;
import life.thoms.wandering_traders.server.data.PlayerEndTraderData;
import life.thoms.wandering_traders.util.MerchantUtil;
import life.thoms.wandering_traders.util.trader.ExclusiveTraderUtil;
import net.minecraft.server.MinecraftServer;

public class ServerEventHandler {

    public static void onServerStarted(MinecraftServer server) {
        PlayerEndTraderData.createServerState(server);
        LostLootData.createServerState(server);

        MerchantUtil.register(server.overworld());
    }

}
