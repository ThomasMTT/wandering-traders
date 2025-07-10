package life.thoms.wandering_traders.fabric.world.gen;

import life.thoms.wandering_traders.entity.*;
import life.thoms.wandering_traders.fabric.registry.TraderEntitiesFabric;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

public class TraderEntitySpawner {

    public static void register() {

        // Add spawns
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DESERT), MobCategory.CREATURE,
                TraderEntitiesFabric.DECORATION_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.SNOWY_BEACH, Biomes.SNOWY_PLAINS, Biomes.SNOWY_SLOPES,
                        Biomes.SNOWY_TAIGA, Biomes.FROZEN_PEAKS),
                MobCategory.CREATURE,
                TraderEntitiesFabric.BOOK_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_FOREST), MobCategory.CREATURE,
                TraderEntitiesFabric.FOREST_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.HAS_SWAMP_HUT), MobCategory.CREATURE,
                TraderEntitiesFabric.POTION_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.PLAINS), MobCategory.CREATURE,
                TraderEntitiesFabric.ANIMAL_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.CRIMSON_FOREST), MobCategory.CREATURE,
                TraderEntitiesFabric.EXCLUSIVE_TRADER, 10, 1, 1);

        // Spawn placements
        SpawnPlacements.register(TraderEntitiesFabric.DECORATION_TRADER, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DecorationTraderEntity::checkMobSpawnRules);

        SpawnPlacements.register(TraderEntitiesFabric.BOOK_TRADER, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BookTraderEntity::checkMobSpawnRules);

        SpawnPlacements.register(TraderEntitiesFabric.FOREST_TRADER, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ForestTraderEntity::checkMobSpawnRules);

        SpawnPlacements.register(TraderEntitiesFabric.POTION_TRADER, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PotionTraderEntity::checkMobSpawnRules);

        SpawnPlacements.register(TraderEntitiesFabric.ANIMAL_TRADER, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AnimalTraderEntity::checkMobSpawnRules);

        SpawnPlacements.register(TraderEntitiesFabric.EXCLUSIVE_TRADER, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ExclusiveTraderEntity::checkMobSpawnRules);

    }

}
