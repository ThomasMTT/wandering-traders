package life.thoms.wandering_traders.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.level.Level;

public class EndTraderEntity extends AbstractTraderEntity {

    public EndTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
    }

}
