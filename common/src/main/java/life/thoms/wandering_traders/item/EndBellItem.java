package life.thoms.wandering_traders.item;

import life.thoms.wandering_traders.entity.EndTraderEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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

public class EndBellItem extends Item {

    public static EntityType<? extends WanderingTrader> END_TRADER_ENTITY;

    public EndBellItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!player.getTags().contains("linked_with_end_trader")) {
            if (!level.isClientSide) {
                ItemStack bellItem = player.getItemInHand(usedHand);
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
                player.displayClientMessage(Component.translatable("end_trader_message.summon_with_bell"), true);
            } else {
                player.playSound(SoundEvents.BELL_BLOCK);
                player.playSound(SoundEvents.ENDERMAN_TELEPORT);
                player.addTag("linked_with_end_trader");
            }

        } else{
            player.displayClientMessage(Component.translatable("end_trader_message.other_still_around"), true);
        }

        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }


}
