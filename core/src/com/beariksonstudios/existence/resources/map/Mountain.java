package com.beariksonstudios.existence.resources.map;


/**
 * Created by Neal on 7/26/2015.
 */
public class Mountain extends MapResource {
    public static String TEX_NAME = "Mountain.png";

    public Mountain(int quantity) {
        super(TEX_NAME, quantity);
    }
    public Mountain(int quantity, float x , float y){
        super(TEX_NAME, quantity, x, y);
    }
}
