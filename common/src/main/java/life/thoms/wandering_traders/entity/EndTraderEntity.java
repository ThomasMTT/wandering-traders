package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.util.trader.EndTraderUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class EndTraderEntity extends AbstractTraderEntity {

    public UUID linkedPlayerUuid = null;
    public String linkedPlayerName = null;
    public EndTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!isClientSide()) {
            if (linkedPlayerUuid == null) {
                linkedPlayerUuid = player.getUUID();
                linkedPlayerName = player.getName().getString();
            }

            if (linkedPlayerUuid != null) {
                getOffers();
                this.offers.removeIf(MerchantOffer::isOutOfStock);
                offers = EndTraderUtil.createOffersFromLostLoot(linkedPlayerUuid, offers);
            }
        }
        return super.mobInteract(player, hand);
    }

}
