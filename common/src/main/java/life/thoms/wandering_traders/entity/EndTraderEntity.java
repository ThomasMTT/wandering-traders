package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.util.trader.EndTraderUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
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
                if (linkedPlayerUuid.equals(player.getUUID())) {
                    offers = EndTraderUtil.createOffersFromLostLoot(linkedPlayerUuid, offers);
                    if (offers.isEmpty()) {
                        player.displayClientMessage(Component.translatable("end_trader_message.no_trades"), true);
                    } else {
                        super.mobInteract(player, hand);
                    }
                } else {
                    MutableComponent message = Component.translatable("end_trader_message.only_trade_with");
                    player.displayClientMessage(message.append(linkedPlayerName).withStyle(ChatFormatting.ITALIC), true);
                }
            }
        }
        return InteractionResult.sidedSuccess(true);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        if (linkedPlayerUuid != null) {
            compound.putUUID("linked_player_uuid", linkedPlayerUuid);
        }
        if (linkedPlayerName != null) {
            compound.putString("linked_player_name", linkedPlayerName);
        }
        super.addAdditionalSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        if (!compound.isEmpty()) {
            if (compound.contains("linked_player_uuid")) {
                linkedPlayerUuid = compound.getUUID("linked_player_uuid");
            }
            if (compound.contains("linked_player_name")) {
                linkedPlayerName = compound.getString("linked_player_name");
            }
        }
        super.readAdditionalSaveData(compound);
    }

}
