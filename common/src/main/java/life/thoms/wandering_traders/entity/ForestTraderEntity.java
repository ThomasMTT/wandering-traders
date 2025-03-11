package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.util.trader.ForestTraderUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ForestTraderEntity extends AbstractTraderEntity {

    public ForestTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void updateTrades() {
        this.offers = ForestTraderUtil.generateOffers();
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.offers == null || this.offers.isEmpty()) {
            this.offers = ForestTraderUtil.generateOffers();
        }
        return super.mobInteract(player, hand);
    }

}
