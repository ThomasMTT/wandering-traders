package life.thoms.wandering_traders;

import life.thoms.wandering_traders.entity.ForestTraderEntity;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityType;

public class ForestTraderTest implements FabricGameTest {

    @GameTest(template = EMPTY_STRUCTURE)
    public void spawnTrader(GameTestHelper context) {
        double x = context.getBounds().minX;
        double y = context.getBounds().minY;
        double z = context.getBounds().minZ;
        ForestTraderEntity trader = new ForestTraderEntity(EntityType.WANDERING_TRADER, context.getLevel());
        trader.setPos(x, y, z);
        context.getLevel().addFreshEntity(trader);
        if (trader.isAlive()) {
            context.succeed();
        }
    }

}