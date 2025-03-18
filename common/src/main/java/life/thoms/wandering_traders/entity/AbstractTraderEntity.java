package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.config.ModConfigs;
import life.thoms.wandering_traders.util.ConfigUtil;
import life.thoms.wandering_traders.util.MerchantUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractTraderEntity extends WanderingTrader {

    public AbstractTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
        this.setDespawnDelay(48000);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Villager.createAttributes();
    }

    @Override
    protected void updateTrades() {/* Empty on purpose to avoid trades being updated by default */}

    @Override
    public @NotNull MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.offers.clear();
        }
        return this.offers;
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!isClientSide() && (this.offers == null || offers.isEmpty())) {
            this.offers = MerchantUtil.getOffersByClass(this);
        }
        return super.mobInteract(player, hand);
    }

    public static boolean checkMobSpawnRules(EntityType<? extends Mob> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        if (ConfigUtil.spawnEnabled(type)) {
            return Mob.checkMobSpawnRules(type, level, spawnType, pos, random);
        }
        return false;
    }

}
