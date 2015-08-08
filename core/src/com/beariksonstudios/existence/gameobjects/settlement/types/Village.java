package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;

/**
 * Created by Neal on 7/7/2015.
 */
public class Village implements SettlementType {
    private Texture rectangle = new Texture("settlements/Rectangle.png");
    private Sprite settlementSprite;

    public Village(Sprite sprite) {
        settlementSprite = sprite;
        settlementSprite.setTexture(rectangle);
    }

    @Override
    public String getName() {
        return "Village";
    }

    @Override
    public void update(float population) {
        settlementSprite.setScale(20f + population / 50);
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
