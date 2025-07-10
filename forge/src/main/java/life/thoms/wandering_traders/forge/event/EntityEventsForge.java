package life.thoms.wandering_traders.forge.event;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.*;
import life.thoms.wandering_traders.forge.registry.TraderEntitiesForge;
import life.thoms.wandering_traders.handler.EntityEventHandler;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WanderingTraders.MOD_ID)
public class EntityEventsForge {

    @SubscribeEvent
    public static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            EntityEventHandler.onEntityLeaveLevel(event.getEntity(), event.getLevel());
        }
    }

    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(TraderEntitiesForge.DECORATION_TRADER.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DecorationTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesForge.BOOK_TRADER.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BookTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesForge.FOREST_TRADER.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ForestTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesForge.POTION_TRADER.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PotionTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesForge.ANIMAL_TRADER.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AnimalTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TraderEntitiesForge.EXCLUSIVE_TRADER.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ExclusiveTraderEntity::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(TraderEntitiesForge.END_TRADER.get(), EndTraderEntity.createAttributes().build());
        event.put(TraderEntitiesForge.DECORATION_TRADER.get(), DecorationTraderEntity.createAttributes().build());
        event.put(TraderEntitiesForge.BOOK_TRADER.get(), BookTraderEntity.createAttributes().build());
        event.put(TraderEntitiesForge.FOREST_TRADER.get(), ForestTraderEntity.createAttributes().build());
        event.put(TraderEntitiesForge.POTION_TRADER.get(), PotionTraderEntity.createAttributes().build());
        event.put(TraderEntitiesForge.ANIMAL_TRADER.get(), AnimalTraderEntity.createAttributes().build());
        event.put(TraderEntitiesForge.EXCLUSIVE_TRADER.get(), ExclusiveTraderEntity.createAttributes().build());
    }

}
