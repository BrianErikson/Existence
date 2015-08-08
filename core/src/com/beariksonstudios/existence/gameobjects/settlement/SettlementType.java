package com.beariksonstudios.existence.gameobjects.settlement;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Neal on 7/7/2015.
 */
public interface SettlementType {
    String getName();

    void update(float population);

    float getGrowthRate();

    String getResources();

    Texture getTexture();
}

