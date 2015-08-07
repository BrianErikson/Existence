package com.beariksonstudios.existence.resources.map;

import com.beariksonstudios.existence.resources.Resource;
import javafx.scene.image.Image;

/**
 * Created by Neal on 7/26/2015.
 */
public class FertileLand extends MapResource {
    public static String TEX_PATH = "MapResources/FertileLand.png";
    public static String NAME = "Fertile Land";
    public static Resource RESOURCE = Resource.GRAIN;

    public FertileLand(int quantity) {
        super(NAME, new Image(TEX_PATH), quantity, RESOURCE);
    }

    public FertileLand(int quantity, double x, double y) {
        super(NAME, new Image(TEX_PATH), quantity, x, y, RESOURCE);
    }
}
