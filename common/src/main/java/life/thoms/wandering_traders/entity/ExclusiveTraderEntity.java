package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.util.ConfigUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.UseItemGoal;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class ExclusiveTraderEntity extends AbstractTraderEntity {

    public ExclusiveTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        // This trader doesn't hide in the night (as he lives in the nether and there is no night there)
        goalSelector.removeAllGoals(goal ->  goal instanceof UseItemGoal);
    }

    public static boolean checkMobSpawnRules(EntityType<? extends Mob> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return !level.dimensionType().bedWorks() && ConfigUtil.spawnEnabled(type);
    }

}
