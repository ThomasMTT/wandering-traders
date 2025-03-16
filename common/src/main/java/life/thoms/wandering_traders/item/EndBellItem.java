package life.thoms.wandering_traders.item;

import life.thoms.wandering_traders.entity.EndTraderEntity;
import life.thoms.wandering_traders.server.data.PlayerEndTraderData;
import life.thoms.wandering_traders.entity.EndTraderMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.UUID;

public class EndBellItem extends Item {

    public static EntityType<? extends WanderingTrader> END_TRADER_ENTITY;

    public EndBellItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        if (!PlayerEndTraderData.PLAYER_END_TRADER_MAP.containsKey(player.getUUID())) {
            ItemStack bellItem = player.getItemInHand(usedHand);
            if (!level.isClientSide) {
                BlockPos playerPos = player.blockPosition();
                BlockPos spawnPos = playerPos.relative(player.getDirection(), 2);
                int spawnY = level.getHeight(Heightmap.Types.WORLD_SURFACE, (int) spawnPos.getX(), (int) spawnPos.getZ());
                spawnPos = new BlockPos(spawnPos.getX(), spawnY, spawnPos.getZ());

                if (!player.isCreative()) {
                    bellItem.setCount(bellItem.getCount() - 1);
                }
                EndTraderEntity trader = new EndTraderEntity(END_TRADER_ENTITY, level);
                trader.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                level.addFreshEntity(trader);
                trader.addLinkedPlayer(player);
                player.displayClientMessage(EndTraderMessage.SUMMON_WITH_BELL.getMessage(), true);
            } else {
                player.swing(usedHand);
                player.playSound(SoundEvents.BELL_BLOCK);
                player.playSound(SoundEvents.ENDERMAN_TELEPORT);
            }

        } else{
            if (!level.isClientSide) {
                MutableComponent message = EndTraderMessage.OTHER_STILL_AROUND.getMessage();
                UUID traderUUID = PlayerEndTraderData.PLAYER_END_TRADER_MAP.get(player.getUUID());
                List<EndTraderEntity> traderList = level.getEntitiesOfClass(EndTraderEntity.class,
                        player.getBoundingBox().inflate(1000), x -> x.getUUID().equals(traderUUID));
                if (traderList.isEmpty()) {
                    player.displayClientMessage(message, true);
                } else {
                    EndTraderEntity trader = traderList.getFirst();
                    player.displayClientMessage(message.append(" (" + (int) trader.getX() + " " + (int)  trader.getY() +
                            " " + (int)  trader.getZ() + ")"), true);
                }
            }
        }

        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }


}
