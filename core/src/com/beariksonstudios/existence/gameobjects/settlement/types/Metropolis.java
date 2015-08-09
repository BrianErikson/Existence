package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;
import com.beariksonstudios.existence.scenes.game.Assets;


/**
 * Created by Neal on 7/18/2015.
 */
public class Metropolis implements SettlementType {
    private Texture star = Assets.manager.get("settlements/Star.png", Texture.class);
    private Image settlementImage;
    public static int MIN_SIZE = 10;
    public static int MAX_SIZE = 200;

    public Metropolis(Image image) {
        settlementImage = image;
        settlementImage.setDrawable(new TextureRegionDrawable(new TextureRegion(star)));
    }

    @Override
    public String getName() {
        return "Metropolis";
    }

    @Override
    public void update(double population) {
        float size = ((float)population/100000) - 20;
        if (size < MIN_SIZE)
            size = MIN_SIZE;
        else if (size > MAX_SIZE)
            size = MAX_SIZE;
        settlementImage.setSize(size, size);
    }

    @Override
    public float getGrowthRate() {
        return 0.0017f;
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
