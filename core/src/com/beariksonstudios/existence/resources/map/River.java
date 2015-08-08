package com.beariksonstudios.existence.resources.map;


/**
 * Created by Neal on 7/26/2015.
 */
public class River extends MapResource {
    public static String TEX_NAME = "River.png";

    public River(int quantity) {
        super(TEX_NAME, quantity);
    }
    public River(int quantity, float x , float y){
        super(TEX_NAME, quantity, x, y);
    }
}
