package life.thoms.wandering_traders.fabric.registry;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.*;
import life.thoms.wandering_traders.item.EndBellItem;
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

    public static final EntityType<GamblingTraderEntity> GAMBLING_TRADER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, WanderingTraders.MOD_ID + ":gambling_trader", EntityType.Builder.of(
                    GamblingTraderEntity::new, MobCategory.CREATURE).sized(0.6F, 2.0F).build("gambling_trader")
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

    public static void register() {
        FabricDefaultAttributeRegistry.register(END_TRADER, EndTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(GAMBLING_TRADER, GamblingTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BOOK_TRADER, BookTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(FOREST_TRADER, ForestTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(POTION_TRADER, ForestTraderEntity.createAttributes());
        EndBellItem.END_TRADER_ENTITY = TraderEntitiesFabric.END_TRADER;
    }

}
