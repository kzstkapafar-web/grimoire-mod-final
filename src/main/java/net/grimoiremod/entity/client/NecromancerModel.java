package net.grimoiremod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.grimoiremod.GrimoireMod;
import net.grimoiremod.entity.WraithEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * Custom hooded-robe silhouette for the Wraith/Necromancer servant.
 * No legs - the flared skirt part covers where legs would be, giving a
 * floating-ghost look. Built with the vanilla model system (no external
 * modeling library needed).
 */
public class NecromancerModel extends EntityModel<WraithEntity> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(GrimoireMod.MODID, "wraith"), "main");

    private final ModelPart head;
    private final ModelPart robe;
    private final ModelPart skirt;
    private final ModelPart rightArm;
    private final ModelPart leftArm;

    public NecromancerModel(ModelPart root) {
        this.head = root.getChild("head");
        this.robe = root.getChild("robe");
        this.skirt = this.robe.getChild("skirt");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.5F, -8.5F, -4.5F, 9, 9, 9, CubeDeformation.NONE),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition robe = root.addOrReplaceChild("robe",
                CubeListBuilder.create()
                        .texOffs(0, 20)
                        .addBox(-4.0F, 0.0F, -3.0F, 8, 8, 6, CubeDeformation.NONE),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        robe.addOrReplaceChild("skirt",
                CubeListBuilder.create()
                        .texOffs(0, 36)
                        .addBox(-5.0F, 8.0F, -4.0F, 10, 10, 8, CubeDeformation.NONE),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        root.addOrReplaceChild("right_arm",
                CubeListBuilder.create()
                        .texOffs(32, 16)
                        .addBox(-2.0F, -1.0F, -2.0F, 4, 13, 4, CubeDeformation.NONE),
                PartPose.offset(-5.5F, 1.0F, 0.0F));

        root.addOrReplaceChild("left_arm",
                CubeListBuilder.create()
                        .texOffs(48, 16)
                        .addBox(-2.0F, -1.0F, -2.0F, 4, 13, 4, CubeDeformation.NONE),
                PartPose.offset(5.5F, 1.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(WraithEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                           float netHeadYaw, float headPitch) {
        // Gentle floating bob - the whole silhouette drifts up and down.
        float bob = Mth.sin(ageInTicks * 0.08F) * 0.6F;
        this.head.y = -2.0F + bob;
        this.robe.y = bob;
        this.rightArm.y = 1.0F + bob;
        this.leftArm.y = 1.0F + bob;

        // Head tracks its target.
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);

        // Arms sway gently while moving.
        this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F) * 0.35F * limbSwingAmount;
        this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.35F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight,
                                int packedOverlay, int color) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        robe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
