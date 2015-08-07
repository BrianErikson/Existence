package com.beariksonstudios.existence.resources.map;

import com.beariksonstudios.existence.resources.Resource;
import javafx.scene.image.Image;

/**
 * Created by Neal on 7/26/2015.
 */
public class Forest extends MapResource {
    public static String TEX_PATH = "MapResources/Forest.png";
    public static String NAME = "Forest";
    public static Resource RESOURCE = Resource.WOOD;

    public Forest(int quantity) {
        super(NAME,new Image(TEX_PATH), quantity, RESOURCE);
    }

    public Forest(int quantity, double x, double y) {
        super(NAME, new Image(TEX_PATH), quantity, x, y, RESOURCE);
    }
}
