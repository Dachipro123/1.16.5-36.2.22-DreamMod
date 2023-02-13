package net.dachi.dream.entity.model;

import net.dachi.dream.Dream;
import net.dachi.dream.entity.custom.PenguinEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PenguinModel extends AnimatedGeoModel<PenguinEntity> {
    @Override
    public ResourceLocation getModelLocation(PenguinEntity object) {
        return new ResourceLocation(Dream.MOD_ID, "geo/penguin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PenguinEntity object) {
        return new ResourceLocation(Dream.MOD_ID, "textures/entity/penguin/penguin.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PenguinEntity animatable) {
        return new ResourceLocation(Dream.MOD_ID, "animations/penguin.animation.json");
    }
}
