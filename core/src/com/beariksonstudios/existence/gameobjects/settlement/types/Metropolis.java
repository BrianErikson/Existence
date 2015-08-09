package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;


/**
 * Created by Neal on 7/18/2015.
 */
public class Metropolis implements SettlementType {
    private Texture star = new Texture("settlements/Star.png");
    private Image settlementImage;

    public Metropolis(Image image) {
        settlementImage = image;
        settlementImage.setDrawable(new SpriteDrawable(new Sprite(star)));
    }

    @Override
    public String getName() {
        return "Metropolis";
    }

    @Override
    public void update(double population) {
        settlementImage.setScale((float)(population * 0.001d));
    }

    @Override
    public float getGrowthRate() {
        return 0.5f;
    }

    @Override
    public String getResources() {
        return "Hoverboard, Instant Obesity reducers, Robot Wives";
    }

    @Override
    public Texture getTexture() {
        return star;
    }
}
