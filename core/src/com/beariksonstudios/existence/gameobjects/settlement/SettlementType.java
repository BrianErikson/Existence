package com.beariksonstudios.existence.gameobjects.settlement;

import com.badlogic.gdx.graphics.Texture;
import com.beariksonstudios.existence.resources.Resource;
import com.beariksonstudios.existence.resources.map.MapResource;

import java.util.ArrayList;

/**
 * Created by Neal on 7/7/2015.
 */
public interface SettlementType {
    String getName();

    void update(double population);

    float getGrowthRate();

    String getResources();

    Texture getTexture();

    ArrayList<Resource> filterResources(ArrayList<MapResource> resources);
}

