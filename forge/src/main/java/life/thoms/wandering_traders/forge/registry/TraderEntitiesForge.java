package life.thoms.wandering_traders.forge.registry;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.*;
import life.thoms.wandering_traders.server.ModRegistryAccess;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TraderEntitiesForge {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
            ForgeRegistries.ENTITY_TYPES, WanderingTraders.MOD_ID);

    public static final RegistryObject<EntityType<EndTraderEntity>> END_TRADER =
            ENTITY_TYPES.register("end_trader", () -> EntityType.Builder.of(EndTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).build("end_trader"));

    public static final RegistryObject<EntityType<DecorationTraderEntity>> DECORATION_TRADER =
            ENTITY_TYPES.register("decoration_trader", () -> EntityType.Builder.of(DecorationTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).build("decoration_trader"));

    public static final RegistryObject<EntityType<BookTraderEntity>> BOOK_TRADER =
            ENTITY_TYPES.register("book_trader", () -> EntityType.Builder.of(BookTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).build("book_trader"));

    public static final RegistryObject<EntityType<ForestTraderEntity>> FOREST_TRADER =
            ENTITY_TYPES.register("forest_trader", () -> EntityType.Builder.of(ForestTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).build("forest_trader"));

    public static final RegistryObject<EntityType<PotionTraderEntity>> POTION_TRADER =
            ENTITY_TYPES.register("potion_trader", () -> EntityType.Builder.of(PotionTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).build("potion_trader"));

    public static final RegistryObject<EntityType<AnimalTraderEntity>> ANIMAL_TRADER =
            ENTITY_TYPES.register("animal_trader", () -> EntityType.Builder.of(AnimalTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).build("animal_trader"));

    public static final RegistryObject<EntityType<ExclusiveTraderEntity>> EXCLUSIVE_TRADER =
            ENTITY_TYPES.register("exclusive_trader", () -> EntityType.Builder.of(ExclusiveTraderEntity::new,
                    MobCategory.CREATURE).sized(0.6F, 2.0F).fireImmune().build("exclusive_trader"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    public static void registerCommon() {
        ModRegistryAccess.addEntityType("end_trader", END_TRADER.get());
        ModRegistryAccess.addEntityType("decoration_trader", DECORATION_TRADER.get());
        ModRegistryAccess.addEntityType("book_trader", BOOK_TRADER.get());
        ModRegistryAccess.addEntityType("forest_trader", FOREST_TRADER.get());
        ModRegistryAccess.addEntityType("potion_trader", POTION_TRADER.get());
        ModRegistryAccess.addEntityType("animal_trader", ANIMAL_TRADER.get());
        ModRegistryAccess.addEntityType("exclusive_trader", EXCLUSIVE_TRADER.get());

    }
}
