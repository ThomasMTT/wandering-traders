package life.thoms.wandering_traders.neoforge.registry;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.BookTraderEntity;
import life.thoms.wandering_traders.entity.EndTraderEntity;
import life.thoms.wandering_traders.entity.ForestTraderEntity;
import life.thoms.wandering_traders.entity.GamblingTraderEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TraderEntitiesNeoForge {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
            BuiltInRegistries.ENTITY_TYPE, WanderingTraders.MOD_ID);

    public static final Supplier<EntityType<EndTraderEntity>> END_TRADER =
            ENTITY_TYPES.register("end_trader", () -> EntityType.Builder.of(EndTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).build("end_trader"));

    public static final Supplier<EntityType<GamblingTraderEntity>> GAMBLING_TRADER =
            ENTITY_TYPES.register("gambling_trader", () -> EntityType.Builder.of(GamblingTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).build("gambling_trader"));

    public static final Supplier<EntityType<BookTraderEntity>> BOOK_TRADER =
            ENTITY_TYPES.register("book_trader", () -> EntityType.Builder.of(BookTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).build("book_trader"));

    public static final Supplier<EntityType<ForestTraderEntity>> FOREST_TRADER =
            ENTITY_TYPES.register("forest_trader", () -> EntityType.Builder.of(ForestTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).build("forest_trader"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
