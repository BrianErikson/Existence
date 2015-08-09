package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;

/**
 * Created by Neal on 7/7/2015.
 */
public class Village implements SettlementType {
    private Texture rectangle = new Texture("settlements/Rectangle.png");
    private Image settlementImage;

    public Village(Image image) {
        settlementImage = image;
        settlementImage.setDrawable(new SpriteDrawable(new Sprite(rectangle)));
    }

    @Override
    public String getName() {
        return "Village";
    }

    @Override
    public void update(double population) {
        settlementImage.setScale((float)(20d + population / 50d));
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
