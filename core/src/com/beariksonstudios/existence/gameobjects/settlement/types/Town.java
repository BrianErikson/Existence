package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;


/**
 * Created by Neal on 7/13/2015.
 */
public class Town implements SettlementType {
    private Texture triangle = new Texture("settlements/Triangle.png");
    private Image settlementImage;

    public Town(Image image) {
        settlementImage = image;
        settlementImage.setDrawable(new SpriteDrawable(new Sprite(triangle)));
    }

    @Override
    public String getName() {
        return "Town";
    }

    @Override
    public void update(double population) {
        settlementImage.setScale((float)(population * 0.01d));
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
