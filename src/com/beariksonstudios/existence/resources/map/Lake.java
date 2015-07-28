package com.beariksonstudios.existence.resources.map;

import javafx.scene.image.Image;

/**
 * Created by Neal on 7/26/2015.
 */
public class Lake extends MapResource {
    public static String TEX_PATH = "MapResources/Lake.png";

    public Lake(int quantity) {
        super(new Image(TEX_PATH), quantity);
    }

    public Lake(int quantity, double x, double y) {
        super(new Image(TEX_PATH), quantity, x, y);
    }
}
