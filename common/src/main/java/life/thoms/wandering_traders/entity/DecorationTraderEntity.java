package life.thoms.wandering_traders.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class DecorationTraderEntity extends AbstractTraderEntity {


    public DecorationTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
    }

    // 10% chance as they spawn way too much even with weight at 1 (crazy minecraft code stuff...)
    public static boolean checkMobSpawnRules(EntityType<? extends Mob> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        if (random.nextInt(10) > 8) {
            return AbstractTraderEntity.checkMobSpawnRules(type, level, spawnType, pos, random);
        }

        return false;
    }

}
