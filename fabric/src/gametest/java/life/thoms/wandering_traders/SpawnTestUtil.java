package life.thoms.wandering_traders;

import life.thoms.wandering_traders.entity.AbstractTraderEntity;
import net.minecraft.gametest.framework.GameTestHelper;

public class SpawnTestUtil {

    public static void spawnTrader(GameTestHelper context, AbstractTraderEntity trader) {
        double x = context.getBounds().minX;
        double y = context.getBounds().minY;
        double z = context.getBounds().minZ;
        trader.setPos(x, y, z);
        context.getLevel().addFreshEntity(trader);
        if (trader.isAlive()) {
            context.succeed();
        }
    }

}
