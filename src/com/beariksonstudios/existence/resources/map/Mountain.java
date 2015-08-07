package com.beariksonstudios.existence.resources.map;

import com.beariksonstudios.existence.resources.Resource;
import javafx.scene.image.Image;

/**
 * Created by Neal on 7/26/2015.
 */
public class Mountain extends MapResource {
    public static String TEX_PATH = "MapResources/Mountain.png";
    public static String NAME = "Mountain";
    public static Resource RESOURCE = Resource.STONE;

    public Mountain(int quantity) {
        super(NAME,new Image(TEX_PATH), quantity, RESOURCE);
    }

    public Mountain(int quantity, double x, double y) {
        super(NAME, new Image(TEX_PATH), quantity, x, y, RESOURCE);
    }
}
