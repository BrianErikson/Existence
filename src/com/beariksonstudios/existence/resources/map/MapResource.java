package com.beariksonstudios.existence.resources.map;

import com.beariksonstudios.existence.scenes.Camera;
import com.beariksonstudios.existence.scenes.game.ClickableObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

import java.util.ArrayList;

/**
 * Created by Neal on 7/26/2015.
 */
public abstract class MapResource implements ClickableObject {
    Rectangle rectangle = new Rectangle();
    private Image texture;
    private int quantity;
    private boolean isTarget = false;

    private Label type;
    private Label quantityLabel;
    private ArrayList<Label> labels = new ArrayList<>();

    private Translate translate;
    private String name;

    public MapResource(String name, Image texture, int quantity) {
        this(name, texture, quantity, 0, 0);
    }

    public MapResource(String name, Image texture, int quantity, double x, double y) {
        this.texture = texture;
        this.name = name;
        this.quantity = quantity;
        translate = new Translate(x, y);
        createShape(x, y, texture.getWidth(), texture.getHeight());

        type = new Label("Resource Type: " + name);
        quantityLabel = new Label("Amount: " + quantity);
        labels.add(type);
        labels.add(quantityLabel);
    }

    private void createShape(double x, double y, double width, double height) {
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setX(x);
        rectangle.setY(y);
    }

    public Image getTexture() {
        return texture;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * subtracts the given amount from quantity
     *
     * @param amount to subtract
     * @return returns true if successful, otherwise false
     */
    public boolean subtractQuantity(int amount) {
        if (quantity - amount <= 0) {
            return false;
        } else {
            quantity -= amount;
            return true;
        }
    }


    public void render(GraphicsContext gc, Camera camera) {


        Point2D position = camera.project(translate.getX(), translate.getY());
        rectangle.setX(position.getX());
        rectangle.setY(position.getY());
        gc.drawImage(texture, position.getX(), position.getY());

        updateUI();
    }

    public void updateUI() {
        if (isTarget) {
            quantityLabel.setText("Amount: " + quantity);
        }
    }

    public void setPosition(double x, double y) {
        translate.setX(x);
        translate.setY(y);
    }

    @Override
    public Shape getShape() {
        return rectangle;
    }

    @Override
    public ArrayList<Label> getLabels() {
        return labels;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setAsTarget() {
        isTarget = true;
    }

    @Override
    public void untarget() {
        isTarget = false;
    }
}
