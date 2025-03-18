package life.thoms.wandering_traders.fabric.registry;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.*;
import life.thoms.wandering_traders.item.EndBellItem;
import life.thoms.wandering_traders.server.ModRegistryAccess;
import life.thoms.wandering_traders.util.ConfigUtil;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class TraderEntitiesFabric {

    public static final EntityType<EndTraderEntity> END_TRADER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, WanderingTraders.MOD_ID + ":end_trader", EntityType.Builder.of(
                    EndTraderEntity::new, MobCategory.CREATURE).sized(0.6F, 2.0F).build("end_trader")
    );

    public static final EntityType<DecorationTraderEntity> DECORATION_TRADER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, WanderingTraders.MOD_ID + ":decoration_trader", EntityType.Builder.of(
                    DecorationTraderEntity::new, MobCategory.CREATURE).sized(0.6F, 2.0F).build("decoration_trader")
    );

    public static final EntityType<BookTraderEntity> BOOK_TRADER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, WanderingTraders.MOD_ID + ":book_trader", EntityType.Builder.of(
                    BookTraderEntity::new, MobCategory.CREATURE).sized(0.6F, 2.0F).build("book_trader")
    );

    public static final EntityType<ForestTraderEntity> FOREST_TRADER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, WanderingTraders.MOD_ID + ":forest_trader", EntityType.Builder.of(
                    ForestTraderEntity::new, MobCategory.CREATURE).sized(0.6F, 2.0F).build("forest_trader")
    );
    public static final EntityType<PotionTraderEntity> POTION_TRADER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, WanderingTraders.MOD_ID + ":potion_trader", EntityType.Builder.of(
                    PotionTraderEntity::new, MobCategory.CREATURE).sized(0.6F, 2.0F).build("potion_trader")
    );

    public static final EntityType<AnimalTraderEntity> ANIMAL_TRADER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, WanderingTraders.MOD_ID + ":animal_trader", EntityType.Builder.of(
                    AnimalTraderEntity::new, MobCategory.CREATURE).sized(0.6F, 2.0F).build("animal_trader")
    );

    public static final EntityType<ExclusiveTraderEntity> EXCLUSIVE_TRADER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, WanderingTraders.MOD_ID + ":exclusive_trader", EntityType.Builder.of(
                    ExclusiveTraderEntity::new, MobCategory.CREATURE).sized(0.6F, 2.0F).fireImmune()
                    .build("exclusive_trader")
    );

    public static void register() {
        FabricDefaultAttributeRegistry.register(END_TRADER, EndTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(DECORATION_TRADER, DecorationTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BOOK_TRADER, BookTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(FOREST_TRADER, ForestTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(POTION_TRADER, PotionTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ANIMAL_TRADER, AnimalTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(EXCLUSIVE_TRADER, ExclusiveTraderEntity.createAttributes());
        registerCommon();
    }

    public static void registerCommon() {
        ModRegistryAccess.addEntityType("end_trader", END_TRADER);
        ModRegistryAccess.addEntityType("decoration_trader", DECORATION_TRADER);
        ModRegistryAccess.addEntityType("book_trader", BOOK_TRADER);
        ModRegistryAccess.addEntityType("forest_trader", FOREST_TRADER);
        ModRegistryAccess.addEntityType("potion_trader", POTION_TRADER);
        ModRegistryAccess.addEntityType("animal_trader", ANIMAL_TRADER);
        ModRegistryAccess.addEntityType("exclusive_trader", EXCLUSIVE_TRADER);
    }

}
