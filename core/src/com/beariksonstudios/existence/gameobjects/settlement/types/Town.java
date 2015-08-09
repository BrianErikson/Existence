package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;
import com.beariksonstudios.existence.scenes.game.Assets;


/**
 * Created by Neal on 7/13/2015.
 */
public class Town implements SettlementType {
    private Texture triangle = Assets.manager.get("settlements/Triangle.png", Texture.class);
    private Image settlementImage;

    public Town(Image image) {
        settlementImage = image;
        settlementImage.setDrawable(new TextureRegionDrawable(new TextureRegion(triangle)));
    }

    @Override
    public String getName() {
        return "Town";
    }

    @Override
    public void update(double population) {
        float size = (float)(population * 0.01d);
        settlementImage.setSize(size, size);
    }

    @Override
    public float getGrowthRate() {
        return 0.5f;
    }

    @Override
    public String getResources() {
        return "Wheat";
    }

    @Override
    public Texture getTexture() {
        return triangle;
    }
}
