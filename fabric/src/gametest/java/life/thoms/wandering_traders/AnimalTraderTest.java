package life.thoms.wandering_traders;

import life.thoms.wandering_traders.entity.AnimalTraderEntity;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityType;

public class AnimalTraderTest implements FabricGameTest {

    @GameTest(template = EMPTY_STRUCTURE)
    public void spawnTrader(GameTestHelper context) {
        AnimalTraderEntity trader = new AnimalTraderEntity(EntityType.WANDERING_TRADER, context.getLevel());
        SpawnTestUtil.spawnTrader(context, trader);
    }

}
