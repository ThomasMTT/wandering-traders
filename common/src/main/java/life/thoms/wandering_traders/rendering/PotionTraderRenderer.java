package life.thoms.wandering_traders.rendering;

import life.thoms.wandering_traders.WanderingTraders;
import life.thoms.wandering_traders.entity.PotionTraderEntity;
import life.thoms.wandering_traders.model.PotionTraderModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PotionTraderRenderer extends MobRenderer<PotionTraderEntity, PotionTraderModel<PotionTraderEntity>> {

    public PotionTraderRenderer(EntityRendererProvider.Context context) {
        super(context, new PotionTraderModel<>(context.bakeLayer(PotionTraderModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PotionTraderEntity entity) {
        return new ResourceLocation(WanderingTraders.MOD_ID, "textures/entity/potion_trader.png");
    }

}
