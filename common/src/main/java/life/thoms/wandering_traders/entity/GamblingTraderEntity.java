package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.util.LootBoxUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.level.Level;

public class GamblingTraderEntity extends AbstractTraderEntity {

    public GamblingTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
        this.offers = LootBoxUtil.LOOT_BOX_OFFERS;
    }

}
