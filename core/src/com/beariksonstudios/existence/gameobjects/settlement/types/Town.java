package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;


/**
 * Created by Neal on 7/13/2015.
 */
public class Town implements SettlementType {
    private Texture triangle = new Texture("settlements/Triangle.png");
    private Sprite settlementSprite;

    public Town(Sprite sprite) {
        settlementSprite = sprite;
        settlementSprite.setTexture(triangle);
    }

    @Override
    public String getName() {
        return "Town";
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
        return "Wheat";
    }

    @Override
    public Texture getTexture() {
        return triangle;
    }
}
