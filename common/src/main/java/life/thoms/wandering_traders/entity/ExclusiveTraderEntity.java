package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.util.trader.ExclusiveTraderUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.UseItemGoal;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

public class ExclusiveTraderEntity extends AbstractTraderEntity {

    public ExclusiveTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        // This trader doesn't hide in the night (as he lives in the nether and there is no light there)
        goalSelector.removeAllGoals(goal ->  goal instanceof UseItemGoal);
    }

    public static boolean checkMobSpawnRules(EntityType<? extends Mob> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        // Allow nether spawning
        BlockPos blockPos = pos.below();
        return spawnType == MobSpawnType.SPAWNER || level.getBlockState(blockPos).isValidSpawn(level, blockPos, type) || !level.dimensionType().bedWorks();
    }

    @Override
    protected void updateTrades() {
        offers = ExclusiveTraderUtil.generateOffers();
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!isClientSide() && (this.offers == null || offers.isEmpty())) {
            offers = ExclusiveTraderUtil.generateOffers();
        }
        return super.mobInteract(player, hand);
    }

}
