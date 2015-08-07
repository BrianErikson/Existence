package com.beariksonstudios.existence.resources.map;

import com.beariksonstudios.existence.resources.Resource;
import javafx.scene.image.Image;

/**
 * Created by Neal on 7/26/2015.
 */
public class River extends MapResource {
    public static String NAME = "River";
    public static Resource RESOURCE = Resource.WATER;
    public River(Image texture, int quantity) {
        super(NAME, texture, quantity, RESOURCE);
    }
}
