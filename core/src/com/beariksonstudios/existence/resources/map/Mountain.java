package com.beariksonstudios.existence.resources.map;


import com.beariksonstudios.existence.resources.Resource;

/**
 * Created by Neal on 7/26/2015.
 */
public class Mountain extends MapResource {
    public static String TEX_NAME = "Mountain.png";
    public static String NAME = "Mountain";
    public static Resource RESOURCE = Resource.STONE;

    public Mountain(int quantity) {
        super(NAME, TEX_NAME, quantity, RESOURCE);
    }
    public Mountain(int quantity, float x , float y){
        super(NAME, TEX_NAME, quantity, x, y, RESOURCE);
    }
}
