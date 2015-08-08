package com.beariksonstudios.existence.resources.map;


import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Neal on 7/26/2015.
 */
public class Mountain extends MapResource {
    public static String TEX_PATH = "MapResources/Mountain.png";

    public Mountain(int quantity) {
        super(new Texture(TEX_PATH), quantity);
    }
    public Mountain(int quantity, float x , float y){
        super(new Texture(TEX_PATH), quantity, x, y);
    }
}
