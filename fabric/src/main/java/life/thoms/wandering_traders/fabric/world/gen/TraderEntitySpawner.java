package life.thoms.wandering_traders.fabric.world.gen;

import life.thoms.wandering_traders.entity.*;
import life.thoms.wandering_traders.fabric.registry.TraderEntitiesFabric;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

public class TraderEntitySpawner {

    public static void register() {

        // Add spawns
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DESERT), MobCategory.CREATURE,
                TraderEntitiesFabric.GAMBLING_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.SNOWY_BEACH, Biomes.SNOWY_PLAINS, Biomes.SNOWY_SLOPES,
                        Biomes.SNOWY_TAIGA, Biomes.FROZEN_PEAKS),
                MobCategory.CREATURE,
                TraderEntitiesFabric.BOOK_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_FOREST), MobCategory.CREATURE,
                TraderEntitiesFabric.FOREST_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.HAS_SWAMP_HUT), MobCategory.CREATURE,
                TraderEntitiesFabric.POTION_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.CRIMSON_FOREST), MobCategory.CREATURE,
                TraderEntitiesFabric.ANIMAL_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), MobCategory.CREATURE,
                TraderEntitiesFabric.EXCLUSIVE_TRADER, 5, 1, 1);

        // Spawn placements
        SpawnPlacements.register(TraderEntitiesFabric.GAMBLING_TRADER, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GamblingTraderEntity::checkMobSpawnRules);

        SpawnPlacements.register(TraderEntitiesFabric.BOOK_TRADER, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BookTraderEntity::checkMobSpawnRules);

        SpawnPlacements.register(TraderEntitiesFabric.FOREST_TRADER, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ForestTraderEntity::checkMobSpawnRules);

        SpawnPlacements.register(TraderEntitiesFabric.POTION_TRADER, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PotionTraderEntity::checkMobSpawnRules);

        SpawnPlacements.register(TraderEntitiesFabric.ANIMAL_TRADER, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AnimalTraderEntity::checkMobSpawnRules);

        SpawnPlacements.register(TraderEntitiesFabric.EXCLUSIVE_TRADER, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ExclusiveTraderEntity::checkMobSpawnRules);

    }

}
