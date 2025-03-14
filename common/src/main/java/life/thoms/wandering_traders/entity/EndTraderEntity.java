package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.server.data.PlayerEndTraderData;
import life.thoms.wandering_traders.util.LostLootUtil;
import life.thoms.wandering_traders.util.trader.EndTraderUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class EndTraderEntity extends AbstractTraderEntity {

    public UUID linkedPlayerUuid = null;
    public String linkedPlayerName = null;

    public EndTraderEntity(EntityType<? extends WanderingTrader> entityType, Level level) {
        super(entityType, level);
    }

    public void addLinkedPlayer(Player player) {
        this.linkedPlayerUuid = player.getUUID();
        this.linkedPlayerName = player.getName().getString();
        PlayerEndTraderData.PLAYER_END_TRADER_MAP.put(player.getUUID(), uuid);
        PlayerEndTraderData.INSTANCE.setDirty();
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!isClientSide()) {
            if (linkedPlayerUuid == null) {
                UUID playerLinkedTraderUUID = PlayerEndTraderData.PLAYER_END_TRADER_MAP.get(player.getUUID());
                if (playerLinkedTraderUUID == null) {
                    if (!LostLootUtil.getPlayerLoot(player).isEmpty()) {
                        addLinkedPlayer(player);
                    }
                } else {
                    player.displayClientMessage(Component.translatable("end_trader_message.other_still_around"), true);
                    return InteractionResult.sidedSuccess(true);
                }
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
            } else {
                player.displayClientMessage(Component.translatable("end_trader_message.no_trades"), true);
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

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Player player) {
            if (!isClientSide()) {

                if (player.isCreative() && player.isCrouching()) {
                    removeFromLinkMap(player);
                    return super.hurt(source, getMaxHealth());
                }
                if (linkedPlayerUuid != null) {
                    if (linkedPlayerUuid.equals(player.getUUID())) {
                        if (offers != null && !offers.isEmpty()) {
                            player.displayClientMessage(Component.translatable("end_trader_message.leave_dim_angry"), true);
                        } else {
                            player.displayClientMessage(Component.translatable("end_trader_message.leave_dim"), true);
                        }
                        goBackToTheEnd(player);
                        return false;
                    }
                }
            } else {
                if (player.isCreative() && player.isCrouching()) {
                    removeFromLinkMap(player);
                }
            }
        }

        if (source.is(DamageTypes.GENERIC_KILL)) {
            if (source.getEntity() instanceof Player player) {
                removeFromLinkMap(player);
            }
            return super.hurt(source, getMaxHealth());
        }
        randomTeleport();

        return false;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (isRemoved()) {
            if (isClientSide()) {
                if (linkedPlayerUuid != null) {
                    Player player = level().getPlayerByUUID(linkedPlayerUuid);
                    if (player != null) {
                        removeFromLinkMap(player);

                        List<ItemStack> merchantLoot = LostLootUtil.getPlayerLoot(player);
                        if (merchantLoot.size() > 53) {
                            player.displayClientMessage(Component.translatable("end_trader_message.leave_dim_too_many_items"), true);
                        } else {
                            boolean lostSomeLoot = random.nextInt(0, 100) > 33;
                            if (offers != null && !offers.isEmpty()) {
                                for (MerchantOffer offer : offers.stream().toList()) {
                                    if (!lostSomeLoot || random.nextBoolean()) {
                                        ItemStack stack = offer.getResult();
                                        merchantLoot.add(stack);
                                    }
                                }
                            }

                            if (lostSomeLoot) {
                                player.displayClientMessage(Component.translatable("end_trader_message.leave_dim_lost"), true);
                            } else {
                                player.displayClientMessage(Component.translatable("end_trader_message.leave_dim"), true);
                            }

                            LostLootUtil.putPlayerLoot(linkedPlayerUuid, merchantLoot);
                        }
                    }
                }
            }
        }
    }

    public void randomTeleport() {
        if (!level().isClientSide()) {
            if (isAlive()) {
                for (int attempts = 0; attempts < 15; attempts++) {
                    double x = getX() + (random.nextDouble() - 0.5) * 64.0;
                    double z = getZ() + (random.nextDouble() - 0.5) * 64.0;
                    double y = getCommandSenderWorld().getHeight(Heightmap.Types.WORLD_SURFACE, (int) x, (int) z);
                    BlockState state = getCommandSenderWorld().getBlockState(new BlockPos((int) x, (int) y - 1, (int) z));

                    if (!(state.getBlock() instanceof LiquidBlock)) {
                        playSound(SoundEvents.ENDERMAN_TELEPORT);
                        teleportTo(x, y, z);
                    }
                }
            }
        }
    }

    public void goBackToTheEnd(Player player) {
        if (!level().isClientSide()) {
            removeFromLinkMap(player);
            discard();
        } else {
            removeFromLinkMap(player);
            playSound(SoundEvents.ENDERMAN_TELEPORT);
        }
    }

    private void removeFromLinkMap(Player player) {
        if (player != null) {
            PlayerEndTraderData.PLAYER_END_TRADER_MAP.remove(player.getUUID());
            PlayerEndTraderData.INSTANCE.setDirty();
        }
    }

}
