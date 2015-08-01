package com.beariksonstudios.existence.resources.map;

import javafx.scene.image.Image;

/**
 * Created by Neal on 7/26/2015.
 */
public class FertileLand extends MapResource {
    public static String TEX_PATH = "MapResources/FertileLand.png";
    public static String NAME = "Fertile Land";

    public FertileLand(int quantity) {
        super(NAME, new Image(TEX_PATH), quantity);
    }

    public FertileLand(int quantity, double x, double y) {
        super(NAME, new Image(TEX_PATH), quantity, x, y);
    }
}
