package com.beariksonstudios.existence.resources.map;

import com.beariksonstudios.existence.resources.Resource;

/**
 * Created by Neal on 7/26/2015.
 */
public class Lake extends MapResource {
    public static String TEX_NAME = "Lake.png";
    public static String NAME = "Lake";
    public static Resource RESOURCE = Resource.FISH;

    public Lake(int quantity) {
        super(NAME, TEX_NAME, quantity, RESOURCE);
    }
    public Lake(int quantity, float x , float y){
        super(NAME, TEX_NAME, quantity, x, y, RESOURCE);
    }
}
