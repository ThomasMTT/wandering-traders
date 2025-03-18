package life.thoms.wandering_traders.entity;

import life.thoms.wandering_traders.config.ModConfigs;
import life.thoms.wandering_traders.message.entity.EndTraderMessage;
import life.thoms.wandering_traders.server.data.PlayerEndTraderData;
import life.thoms.wandering_traders.util.LostLootUtil;
import life.thoms.wandering_traders.util.trader.EndTraderUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.UseItemGoal;
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

    @Override
    protected void registerGoals() {
        super.registerGoals();
        // This trader doesn't hide in the night
        goalSelector.removeAllGoals(goal -> goal instanceof UseItemGoal);
    }

    public void addLinkedPlayer(Player player) {
        this.linkedPlayerUuid = player.getUUID();
        this.linkedPlayerName = player.getName().getString();
        PlayerEndTraderData.PLAYER_END_TRADER_MAP.put(player.getUUID(), uuid);
        PlayerEndTraderData.INSTANCE.setDirty();
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand) {

        if (!ModConfigs.ENABLE_LOOT_RECOVERY) {
            if (player.getServer() != null) {
                player.displayClientMessage(player.getServer().isDedicatedServer()
                                ? EndTraderMessage.FEATURE_DISABLED.getMessage()
                                : EndTraderMessage.FEATURE_DISABLED_SP.getMessage(),
                        true);
            }
            return InteractionResult.sidedSuccess(true);
        }

        if (!isClientSide()) {
            if (linkedPlayerUuid == null) {
                UUID playerLinkedTraderUUID = PlayerEndTraderData.PLAYER_END_TRADER_MAP.get(player.getUUID());
                if (playerLinkedTraderUUID == null) {
                    if (!LostLootUtil.getPlayerLoot(player).isEmpty()) {
                        addLinkedPlayer(player);
                    }
                } else {
                    player.displayClientMessage(EndTraderMessage.OTHER_STILL_AROUND.getMessage(), true);
                    return InteractionResult.sidedSuccess(true);
                }
            }

            if (linkedPlayerUuid != null) {
                getOffers();
                this.offers.removeIf(MerchantOffer::isOutOfStock);
                if (linkedPlayerUuid.equals(player.getUUID())) {
                    offers = EndTraderUtil.createOffersFromLostLoot(linkedPlayerUuid, offers);
                    if (offers.isEmpty()) {
                        player.displayClientMessage(EndTraderMessage.NO_TRADES.getMessage(), true);
                    } else {
                        super.mobInteract(player, hand);
                    }
                } else {
                    player.displayClientMessage(EndTraderMessage.ONLY_TRADE_WITH.getMessage(linkedPlayerName), true);
                }
            } else {
                player.displayClientMessage(EndTraderMessage.NO_TRADES.getMessage(), true);
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
        if (source.getEntity() instanceof Player player && !isClientSide()) {
            return handlePlayerDamage(source, player);
        }

        if (source.is(DamageTypes.GENERIC_KILL)) {
            removeFromLinkMap();
            return super.hurt(source, getMaxHealth());
        }

        if (!source.is(DamageTypes.FALL)) {
            randomTeleport();
        }

        return false;
    }

    private boolean handlePlayerDamage(DamageSource source, Player player) {
        if (player.isCreative() && player.isCrouching()) {
            removeFromLinkMap();
            return super.hurt(source, getMaxHealth());
        }

        if (linkedPlayerUuid != null && linkedPlayerUuid.equals(player.getUUID())) {
            EndTraderMessage messageHolder = (offers != null && !offers.isEmpty())
                    ? EndTraderMessage.LEAVE_DIM_ANGRY
                    : EndTraderMessage.LEAVE_DIM;
            player.displayClientMessage(messageHolder.getMessage(), true);
            goBackToTheEnd();
            return false;
        }
        randomTeleport();
        return false;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (isRemoved() && !isClientSide() && linkedPlayerUuid != null) {
            Player player = level().getPlayerByUUID(linkedPlayerUuid);
            if (player != null) {
                handleDespawn(player);
            }
        }
    }

    private void handleDespawn(Player player) {
        removeFromLinkMap();
        List<ItemStack> merchantLoot = LostLootUtil.getPlayerLoot(player);

        if (merchantLoot.size() > 53) {
            player.displayClientMessage(EndTraderMessage.LEAVE_DIM_TOO_MANY_ITEMS.getMessage(), true);
        } else {
            boolean lostSomeLoot = random.nextInt(0, 100) > 33;
            int originalLootSize = merchantLoot.size();
            if (offers != null && !offers.isEmpty()) {
                for (MerchantOffer offer : offers) {
                    if (!lostSomeLoot || random.nextBoolean()) {
                        ItemStack stack = offer.getResult();
                        merchantLoot.add(stack);
                    }
                }
            }
            int lootAddedCount = merchantLoot.size() - originalLootSize;
            EndTraderMessage despawnMessage = getDespawnMessage(lostSomeLoot, lootAddedCount);
            player.displayClientMessage(despawnMessage.getMessage(), true);
            LostLootUtil.putPlayerLoot(linkedPlayerUuid, merchantLoot);
        }
    }

    private EndTraderMessage getDespawnMessage(boolean lostSomeLoot, int lootAddedCount) {
        EndTraderMessage messageHolder = EndTraderMessage.LEAVE_DIM;
        if (lostSomeLoot && offers.size() != lootAddedCount) {
            if (lootAddedCount == 0) {
                messageHolder = EndTraderMessage.LEAVE_DIM_LOST_ALL;
            } else {
                messageHolder = EndTraderMessage.LEAVE_DIM_LOST;
            }
        }
        return messageHolder;
    }

    public void randomTeleport() {
        if (isAlive()) {
            for (int attempts = 0; attempts < 15; attempts++) {
                double x = getX() + (random.nextDouble() - 0.5) * 16;
                double z = getZ() + (random.nextDouble() - 0.5) * 16;
                double y = getCommandSenderWorld().getHeight(Heightmap.Types.WORLD_SURFACE, (int) x, (int) z);
                BlockState state = getCommandSenderWorld().getBlockState(new BlockPos((int) x, (int) y - 1, (int) z));

                if (!(state.getBlock() instanceof LiquidBlock)) {
                    playSound(SoundEvents.ENDERMAN_TELEPORT);
                    teleportTo(x, y, z);
                }
            }
        }
    }

    public void goBackToTheEnd() {
        if (!level().isClientSide()) {
            removeFromLinkMap();
            discard();
        } else {
            playSound(SoundEvents.ENDERMAN_TELEPORT);
        }
    }

    private void removeFromLinkMap() {
        if (linkedPlayerUuid != null) {
            PlayerEndTraderData.PLAYER_END_TRADER_MAP.remove(linkedPlayerUuid);
            PlayerEndTraderData.INSTANCE.setDirty();
        }
    }

}
