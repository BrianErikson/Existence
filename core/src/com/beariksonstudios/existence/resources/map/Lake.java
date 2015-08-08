package com.beariksonstudios.existence.resources.map;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Neal on 7/26/2015.
 */
public class Lake extends MapResource {
    public static String TEX_PATH = "MapResources/Lake.png";

    public Lake(int quantity) {
        super(new Texture(TEX_PATH), quantity);
    }

    public Lake(int quantity, float x, float y) {
        super(new Texture(TEX_PATH), quantity, x, y);
    }
}
