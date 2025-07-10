package life.thoms.wandering_traders.fabric.registry;

import life.thoms.wandering_traders.config.ModConfigs;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class TraderLootTableModifiers {

    private static final ResourceLocation ENDERMAN_LOOT = new ResourceLocation("minecraft:entities/enderman");

    public static void register() {
        LootTableEvents.MODIFY.register((resourceManager, lootDataManager, location, builder, source) -> {
            if (ModConfigs.ENABLE_END_BELL_DROP && ModConfigs.ENABLE_LOOT_RECOVERY) { // TODO test
                if (ENDERMAN_LOOT.equals(location)) {
                    LootPool.Builder poolBuilder = LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1f))
                            .conditionally(LootItemRandomChanceCondition.randomChance(0.03f).build())
                            .with(LootItem.lootTableItem(TraderItemsFabric.END_BELL).build())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1)));

                    builder.pool(poolBuilder.build());
                }
            }
        });
    }

}
