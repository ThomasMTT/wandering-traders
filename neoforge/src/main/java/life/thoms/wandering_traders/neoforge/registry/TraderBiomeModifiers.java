package life.thoms.wandering_traders.neoforge.registry;

import life.thoms.wandering_traders.WanderingTraders;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class TraderBiomeModifiers {

    public static final ResourceKey<BiomeModifier> SPAWN_END_TRADER = registerKey("spawn_end_trader.json");
    public static final ResourceKey<BiomeModifier> SPAWN_GAMBLING_TRADER = registerKey("spawn_gambling_trader");
    public static final ResourceKey<BiomeModifier> SPAWN_BOOK_TRADER = registerKey("spawn_book_trader");
    public static final ResourceKey<BiomeModifier> SPAWN_FOREST_TRADER = registerKey("spawn_forest_trader");

    public static void register(BootstrapContext<BiomeModifier> context) {

        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(SPAWN_END_TRADER, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                List.of(new MobSpawnSettings.SpawnerData(TraderEntitiesNeoForge.END_TRADER.get(),
                        1, 1, 1))
        ));
        context.register(SPAWN_GAMBLING_TRADER, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_DESERT_PYRAMID),
                List.of(new MobSpawnSettings.SpawnerData(TraderEntitiesNeoForge.GAMBLING_TRADER.get(),
                        1, 1, 1))
        ));
        context.register(SPAWN_BOOK_TRADER, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_IGLOO),
                List.of(new MobSpawnSettings.SpawnerData(TraderEntitiesNeoForge.BOOK_TRADER.get(),
                        1, 1, 1))
        ));
        context.register(SPAWN_FOREST_TRADER, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                List.of(new MobSpawnSettings.SpawnerData(TraderEntitiesNeoForge.FOREST_TRADER.get(),
                        1, 1, 1))
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                new ResourceLocation(WanderingTraders.MOD_ID + ":" + name));
    }

}
