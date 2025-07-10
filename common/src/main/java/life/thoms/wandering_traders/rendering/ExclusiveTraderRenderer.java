package life.thoms.wandering_traders.rendering;

import life.thoms.wandering_traders.WanderingTraders;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WanderingTraderRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.WanderingTrader;
import org.jetbrains.annotations.NotNull;

public class ExclusiveTraderRenderer extends WanderingTraderRenderer {

    public ExclusiveTraderRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(WanderingTrader entity) {
        return new ResourceLocation(WanderingTraders.MOD_ID, "textures/entity/exclusive_trader.png");
    }

}
