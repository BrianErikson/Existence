package com.beariksonstudios.existence.resources.map;

import javafx.scene.image.Image;

/**
 * Created by Neal on 7/26/2015.
 */
public class Mountain extends MapResource {
    public static String TEX_PATH = "MapResources/Mountain.png";

    public Mountain(int quantity) {
        super(new Image(TEX_PATH), quantity);
    }
    public Mountain(int quantity, double x , double y){
        super(new Image(TEX_PATH), quantity, x, y);
    }
}