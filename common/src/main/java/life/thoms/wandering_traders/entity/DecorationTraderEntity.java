package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.util.trader.DecorationTraderUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.level.Level;

public class DecorationTraderEntity extends AbstractTraderEntity {

    public DecorationTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
        if (!isClientSide() && (this.offers == null || offers.isEmpty())) {
            this.offers = DecorationTraderUtil.generateOffers();
        }
    }

}
