package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;
import com.beariksonstudios.existence.scenes.game.Assets;

/**
 * Created by Neal on 7/7/2015.
 */
public class Village implements SettlementType {
    private Texture rectangle = Assets.manager.get("settlements/Rectangle.png", Texture.class);
    private Image settlementImage;

    public Village(Image image) {
        settlementImage = image;
        settlementImage.setDrawable(new TextureRegionDrawable(new TextureRegion(rectangle)));
    }

    @Override
    public String getName() {
        return "Village";
    }

    @Override
    public void update(double population) {
        float size = (float)(20d + population / 50d);
        settlementImage.setSize(size, size);
    }

    @Override
    public float getGrowthRate() {
        return 0.5f;
    }

    @Override
    public String getResources() {
        return "Water";
    }

    @Override
    public Texture getTexture() {
        return rectangle;
    }

}
