package life.thoms.wandering_traders.neoforge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.*;
import life.thoms.wandering_traders.handler.EntityEventHandler;
import life.thoms.wandering_traders.neoforge.registry.TraderEntitiesNeoForge;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
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
        event.register(TraderEntitiesNeoForge.DECORATION_TRADER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DecorationTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesNeoForge.BOOK_TRADER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BookTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesNeoForge.FOREST_TRADER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ForestTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesNeoForge.POTION_TRADER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PotionTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesNeoForge.ANIMAL_TRADER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AnimalTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesNeoForge.EXCLUSIVE_TRADER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ExclusiveTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(TraderEntitiesNeoForge.END_TRADER.get(), EndTraderEntity.createAttributes().build());
        event.put(TraderEntitiesNeoForge.DECORATION_TRADER.get(), DecorationTraderEntity.createAttributes().build());
        event.put(TraderEntitiesNeoForge.BOOK_TRADER.get(), BookTraderEntity.createAttributes().build());
        event.put(TraderEntitiesNeoForge.FOREST_TRADER.get(), ForestTraderEntity.createAttributes().build());
        event.put(TraderEntitiesNeoForge.POTION_TRADER.get(), PotionTraderEntity.createAttributes().build());
        event.put(TraderEntitiesNeoForge.ANIMAL_TRADER.get(), AnimalTraderEntity.createAttributes().build());
        event.put(TraderEntitiesNeoForge.EXCLUSIVE_TRADER.get(), ExclusiveTraderEntity.createAttributes().build());
    }

}
