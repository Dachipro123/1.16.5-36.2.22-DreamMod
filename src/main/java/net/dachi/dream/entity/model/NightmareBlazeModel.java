package net.dachi.dream.entity.model;

import net.dachi.dream.Dream;
import net.dachi.dream.entity.custom.NightmareBlazeEntity;
import net.dachi.dream.entity.custom.NightmareBlazeEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NightmareBlazeModel extends AnimatedGeoModel<NightmareBlazeEntity> {
    @Override
    public ResourceLocation getModelLocation(NightmareBlazeEntity object) {
        return new ResourceLocation(Dream.MOD_ID, "geo/nightmare_blaze.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NightmareBlazeEntity object) {
        return new ResourceLocation(Dream.MOD_ID, "textures/entity/nightmare_blaze/nightmare_blaze.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NightmareBlazeEntity animatable) {
        return new ResourceLocation(Dream.MOD_ID, "animations/nightmare_blaze.animation.json");
    }
}
