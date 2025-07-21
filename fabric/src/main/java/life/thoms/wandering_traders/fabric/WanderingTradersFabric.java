package life.thoms.wandering_traders.fabric;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.fabric.config.ModConfigFabric;
import life.thoms.wandering_traders.fabric.event.EntityEventsFabric;
import life.thoms.wandering_traders.fabric.event.PlayerEventsFabric;
import life.thoms.wandering_traders.fabric.event.ServerEventsFabric;
import life.thoms.wandering_traders.fabric.registry.*;
import life.thoms.wandering_traders.fabric.world.gen.TraderEntitySpawner;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class WanderingTradersFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfigFabric.class, GsonConfigSerializer::new);
        ModConfigFabric.CONFIG = AutoConfig.getConfigHolder(ModConfigFabric.class).getConfig();
        ModConfigFabric.updateSharedConfigs();
        WanderingTraders.init();
        EntityEventsFabric.register();
        TraderEntitiesFabric.register();
        TraderItemsFabric.register();
        TraderCreativeTabFabric.register();
        ServerEventsFabric.register();
        PlayerEventsFabric.register();
        TraderEntitySpawner.register();
        TraderLootTableModifiers.register();
        TraderCommandsFabric.register();
    }

}
