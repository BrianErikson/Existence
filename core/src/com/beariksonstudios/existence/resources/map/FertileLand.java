package com.beariksonstudios.existence.resources.map;

/**
 * Created by Neal on 7/26/2015.
 */
public class FertileLand extends MapResource {
    public static String TEX_NAME = "FertileLand.png";

    public FertileLand(int quantity) {
        super(TEX_NAME, quantity);
    }
    public FertileLand(int quantity, float x , float y){
        super(TEX_NAME, quantity, x, y);
    }
}
