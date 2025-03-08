package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.util.trader.EndTraderUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
                if (linkedPlayerUuid == player.getUUID()) {
                    offers = EndTraderUtil.createOffersFromLostLoot(linkedPlayerUuid, offers);
                    if (offers.isEmpty()) {
                        player.displayClientMessage(Component.translatable("end_trader_message.no_trades"), true);
                    }
                } else {
                    MutableComponent message = Component.translatable("end_trader_message.only_trade_with");
                    player.displayClientMessage(message.append(linkedPlayerName).withStyle(ChatFormatting.ITALIC), true);
                }
            }
        }
        return super.mobInteract(player, hand);
    }

}
