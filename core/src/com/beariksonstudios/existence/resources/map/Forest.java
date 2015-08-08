package com.beariksonstudios.existence.resources.map;

/**
 * Created by Neal on 7/26/2015.
 */
public class Forest extends MapResource {
    public static String TEX_NAME = "Forest.png";

    public Forest(int quantity) {
        super(TEX_NAME, quantity);
    }
    public Forest(int quantity, float x , float y){
        super(TEX_NAME, quantity, x, y);
    }
}
