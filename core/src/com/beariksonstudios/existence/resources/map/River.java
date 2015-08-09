package com.beariksonstudios.existence.resources.map;


import com.beariksonstudios.existence.resources.Resource;

/**
 * Created by Neal on 7/26/2015.
 */
public class River extends MapResource {
    public static String TEX_NAME = "River.png";
    public static String NAME = "River";
    public static Resource RESOURCE = Resource.WATER;

    public River(int quantity) {
        super(NAME, TEX_NAME, quantity, RESOURCE);
    }
    public River(int quantity, float x , float y){
        super(NAME, TEX_NAME, quantity, x, y, RESOURCE);
    }
}
