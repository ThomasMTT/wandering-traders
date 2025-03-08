package life.thoms.wandering_traders.fabric.registry;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.EndTraderEntity;
import life.thoms.wandering_traders.entity.GamblingTraderEntity;
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

    public static void register() {
        FabricDefaultAttributeRegistry.register(TraderEntitiesFabric.END_TRADER, EndTraderEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(TraderEntitiesFabric.GAMBLING_TRADER, EndTraderEntity.createAttributes());
    }

}
