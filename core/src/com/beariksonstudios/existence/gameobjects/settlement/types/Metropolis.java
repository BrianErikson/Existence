package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;


/**
 * Created by Neal on 7/18/2015.
 */
public class Metropolis implements SettlementType {
    private Texture star = new Texture("settlements/Star.png");
    private Sprite settlementSprite;

    public Metropolis(Sprite sprite) {
        settlementSprite = sprite;
        settlementSprite.setTexture(star);
    }

    @Override
    public String getName() {
        return "Metropolis";
    }

    @Override
    public void update(float population) {
        settlementSprite.setScale(population * 0.001f);
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
