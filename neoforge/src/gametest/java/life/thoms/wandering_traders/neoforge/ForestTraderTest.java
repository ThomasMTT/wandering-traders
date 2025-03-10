package life.thoms.wandering_traders.neoforge;

import life.thoms.wandering_traders.neoforge.registry.TraderEntitiesNeoForge;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.neoforged.neoforge.gametest.GameTestHolder;

@GameTestHolder
public class ForestTraderTest {

    @GameTest
    public void spawnTrader(GameTestHelper context) {
        WanderingTrader trader = context.spawn(TraderEntitiesNeoForge.FOREST_TRADER.get(), new BlockPos(0, 1, 0));
        if (trader.isAlive()) {
            context.succeed();
        }
    }

}
