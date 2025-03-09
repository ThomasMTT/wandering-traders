package life.thoms.wandering_traders.handler;

import life.thoms.wandering_traders.server.data.LostLootData;
import life.thoms.wandering_traders.util.LootBoxUtil;
import net.minecraft.server.MinecraftServer;

public class ServerEventHandler {

    public static void onServerStarted(MinecraftServer server) {
        LostLootData.createServerState(server);
        LootBoxUtil.register();
    }

}
