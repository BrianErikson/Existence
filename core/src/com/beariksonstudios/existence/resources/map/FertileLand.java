package com.beariksonstudios.existence.resources.map;

import com.beariksonstudios.existence.resources.Resource;

/**
 * Created by Neal on 7/26/2015.
 */
public class FertileLand extends MapResource {
    public static String TEX_NAME = "FertileLand.png";
    public static String NAME = "Fertile Land";
    public static Resource RESOURCE = Resource.GRAIN;

    public FertileLand(int quantity) {
        super(NAME, TEX_NAME, quantity, RESOURCE);
    }
    public FertileLand(int quantity, float x , float y){
        super(NAME, TEX_NAME, quantity, x, y, RESOURCE);
    }
}
