package com.beariksonstudios.existence.resources.map;

import javafx.scene.image.Image;

/**
 * Created by Neal on 7/26/2015.
 */
public class Forest extends MapResource {
    public static String TEX_PATH = "MapResources/Forest.png";
    public static String NAME = "Forest";

    public Forest(int quantity) {
        super(NAME, new Image(TEX_PATH), quantity);
    }

    public Forest(int quantity, double x, double y) {
        super(NAME, new Image(TEX_PATH), quantity, x, y);
    }
}
