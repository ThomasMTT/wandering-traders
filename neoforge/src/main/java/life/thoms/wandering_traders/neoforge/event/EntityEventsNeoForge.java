package life.thoms.wandering_traders.neoforge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.BookTraderEntity;
import life.thoms.wandering_traders.entity.ForestTraderEntity;
import life.thoms.wandering_traders.entity.GamblingTraderEntity;
import life.thoms.wandering_traders.handler.EntityEventHandler;
import life.thoms.wandering_traders.neoforge.registry.TraderEntitiesNeoForge;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;

@EventBusSubscriber(modid = WanderingTraders.MOD_ID)
public class EntityEventsNeoForge {

    @SubscribeEvent
    private static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            EntityEventHandler.onEntityLeaveLevel(event.getEntity(), event.getLevel());
        }
    }

    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(TraderEntitiesNeoForge.GAMBLING_TRADER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GamblingTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesNeoForge.BOOK_TRADER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BookTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesNeoForge.FOREST_TRADER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ForestTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

}
