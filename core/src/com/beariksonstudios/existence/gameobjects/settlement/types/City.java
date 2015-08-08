package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;


/**
 * Created by Neal on 7/17/2015.
 */
public class City implements SettlementType {
    private Texture diamond = new Texture("settlements/Diamond.png");
    private Sprite settlementSprite;

    public City(Sprite sprite) {
        settlementSprite = sprite;
        settlementSprite.setTexture(diamond);
    }

    @Override
    public String getName() {
        return "City";
    }

    @Override
    public void update(float population) {
        settlementSprite.setScale(population * 0.01f);
    }

    @Override
    public float getGrowthRate() {
        return 0.5f;
    }

    @Override
    public String getResources() {
        return "HoloLens/Oculus Rifts";
    }

    @Override
    public Texture getTexture() {
        return diamond;
    }
}
