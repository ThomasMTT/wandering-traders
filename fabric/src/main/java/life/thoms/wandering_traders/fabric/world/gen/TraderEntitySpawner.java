package life.thoms.wandering_traders.fabric.world.gen;

import life.thoms.wandering_traders.fabric.registry.TraderEntitiesFabric;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;

public class TraderEntitySpawner {

    public static void register() {

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DESERT), MobCategory.CREATURE,
                TraderEntitiesFabric.GAMBLING_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.SNOWY_BEACH, Biomes.SNOWY_PLAINS, Biomes.SNOWY_SLOPES,
                        Biomes.SNOWY_TAIGA, Biomes.FROZEN_PEAKS),
                MobCategory.CREATURE,
                TraderEntitiesFabric.BOOK_TRADER, 1, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_FOREST), MobCategory.CREATURE,
                TraderEntitiesFabric.FOREST_TRADER, 1, 1, 1);

    }

}
