package com.beariksonstudios.existence.resources.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Neal on 7/26/2015.
 */
public abstract class MapResource extends Actor {
    private Texture texture;
    private int quantity;

    public MapResource(Texture texture, int quantity){
        this.texture = texture;
        this.quantity = quantity;
    }

    public MapResource(Texture texture, int quantity, float x, float y){
        this(texture, quantity);
        setPosition(x, y);
    }

    public Texture getTexture() {
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(texture, getX(), getY());
    }
}
