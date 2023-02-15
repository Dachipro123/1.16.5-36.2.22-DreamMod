package net.dachi.dream.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.dachi.dream.Dream;
import net.dachi.dream.entity.custom.NightmareBlazeEntity;
import net.dachi.dream.entity.model.NightmareBlazeModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class NightmareBlazeRenderer extends GeoEntityRenderer<NightmareBlazeEntity> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Dream.MOD_ID, "textures/entity/nightmare_blaze/nightmare_blaze.png");

    public NightmareBlazeRenderer(EntityRendererManager renderManager) {
        super(renderManager, new NightmareBlazeModel());
        this.shadowSize = 0.3f;
    }

    @Override
    public ResourceLocation getEntityTexture(NightmareBlazeEntity entity) {
        return TEXTURE;
    }

    @Override
    public RenderType getRenderType(NightmareBlazeEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(0.8f,0.8f,0.8f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
