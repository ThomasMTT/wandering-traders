package life.thoms.wandering_traders.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.level.Level;

public abstract class AbstractTraderEntity extends WanderingTrader {

    public AbstractTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Villager.createAttributes();
    }

}
