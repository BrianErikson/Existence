package com.beariksonstudios.existence.resources.map;

import com.beariksonstudios.existence.scenes.Transform;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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

    public MapResource(Image texture, int quantity, double x, double y){
        this.texture = texture;
        this.quantity = quantity;
        translate = new Translate(x, y);
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

            Point2D position = camera.project(translate.getX(), translate.getY());
            gc.drawImage(texture, position.getX(), position.getY());
    }
    public void setPosition(double x, double y){
        translate.setX(x);
        translate.setY(y);
    }
}
