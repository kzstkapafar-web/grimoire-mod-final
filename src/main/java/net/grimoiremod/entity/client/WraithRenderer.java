package net.grimoiremod.entity.client;

import net.grimoiremod.GrimoireMod;
import net.grimoiremod.entity.WraithEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WraithRenderer extends MobRenderer<WraithEntity, NecromancerModel> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(GrimoireMod.MODID, "textures/entity/wraith.png");

    public WraithRenderer(EntityRendererProvider.Context context) {
        super(context, new NecromancerModel(context.bakeLayer(NecromancerModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(WraithEntity entity) {
        return TEXTURE;
    }
}
