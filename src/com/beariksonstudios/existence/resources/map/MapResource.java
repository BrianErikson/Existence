package com.beariksonstudios.existence.resources.map;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

/**
 * Created by Neal on 7/26/2015.
 */
public abstract class MapResource {
    private Image texture;
    private int quantity;

    private Translate translate;

    public MapResource(Image texture, int quantity){
        this.texture = texture;
        this.quantity = quantity;
        translate = new Translate();
    }
    public Image getTexture() {
        return texture;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * subtracts the given amount from quantity
     * @param amount to subtract
     * @return returns true if successful, otherwise false
     *
     */
    public boolean subtractQuantity(int amount){
        if(quantity - amount <= 0){
            return false;
        }
        else {
            quantity -= amount;
            return  true;
        }
    }


    public void render(GraphicsContext gc, Transform camera) {
            Point2D position = camera.transform(translate.getX(), translate.getY());
            gc.drawImage(texture, position.getX(), position.getY());
    }
}
