package com.beariksonstudios.existence.resources.map;

/**
 * Created by Neal on 7/26/2015.
 */
public class Lake extends MapResource {
    public static String TEX_NAME = "Lake.png";

    public Lake(int quantity) {
        super(TEX_NAME, quantity);
    }
    public Lake(int quantity, float x , float y){
        super(TEX_NAME, quantity, x, y);
    }
}
