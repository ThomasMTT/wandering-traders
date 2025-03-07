package life.thoms.wandering_traders.neoforge.registry;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.EndTraderEntity;
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
            ENTITY_TYPES.register("end_trader", () -> EntityType.Builder.of(EndTraderEntity::new, MobCategory.CREATURE).sized(0.6F, 2.0F).build("end_trader"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
