package com.beariksonstudios.existence.resources.map;

import com.beariksonstudios.existence.resources.Resource;

/**
 * Created by Neal on 7/26/2015.
 */
public class Forest extends MapResource {
    public static String TEX_NAME = "Forest.png";
    public static String NAME = "Forest";
    public static Resource RESOURCE = Resource.WOOD;

    public Forest(int quantity) {
        super(NAME, TEX_NAME, quantity, RESOURCE);
    }
    public Forest(int quantity, float x , float y){
        super(NAME, TEX_NAME, quantity, x, y, RESOURCE);
    }
}
