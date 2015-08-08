package com.beariksonstudios.existence.resources.map;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Neal on 7/26/2015.
 */
public class Forest extends MapResource {
    public static String TEX_PATH = "MapResources/Forest.png";

    public Forest(int quantity) {
        super(new Texture(TEX_PATH), quantity);
    }
    public Forest(int quantity, float x , float y){
        super(new Texture(TEX_PATH), quantity, x, y);
    }
}
