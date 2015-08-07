package com.beariksonstudios.existence.resources.map;

import com.beariksonstudios.existence.resources.Resource;
import com.beariksonstudios.existence.scenes.Camera;
import com.beariksonstudios.existence.scenes.game.ClickableObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Neal on 7/26/2015.
 */
public abstract class MapResource implements ClickableObject {
    Rectangle rectangle = new Rectangle();
    private Image texture;
    private double quantity;
    private boolean isTarget = false;

    private Label type;
    private Label quantityLabel;
    private Label claim;
    private Label resource;
    private ArrayList<Label> labels = new ArrayList<>();

    private Translate translate;
    private String name;
    private String claimName = "Unclaimed";
    private ArrayList<Resource> specificResources = new ArrayList<>();

    public MapResource(String name, Image texture, int quantity, Resource... specificResources) {
        this(name,texture, quantity, 0, 0, specificResources);
    }

    public MapResource(String name, Image texture, int quantity, double x, double y, Resource... specificResources) {
        this.texture = texture;
        this.name = name;
        this.quantity = quantity;
        translate = new Translate(x, y);
        createShape(x, y, texture.getWidth(), texture.getHeight());
        Collections.addAll(this.specificResources, specificResources);
        type = new Label("Resource Type: " + name);
        quantityLabel = new Label("Amount: " + quantity);
        claim = new Label("Claimed By: " + claimName);

        String resources = "";
        for (Resource resource : this.specificResources) {
            resources += resource.toString();
            resources += " ";
        }

        resource = new Label("Resources Available: " + resources);

        labels.add(type);
        labels.add(quantityLabel);
        labels.add(resource);
        labels.add(claim);
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

    public double getQuantity() {
        return quantity;
    }

    /**
     * subtracts the given amount from quantity
     *
     * @param amount to subtract
     * @return returns true if successful, otherwise false
     */
    public boolean subtractQuantity(double amount) {
        if (quantity - amount <= 0) {
            return false;
        }
        else {
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
            DecimalFormat df = new DecimalFormat("#.00");
            quantityLabel.setText("Amount: " + df.format(quantity));
        }
    }

    public void setPosition(double x, double y) {
        translate.setX(x);
        translate.setY(y);
    }
    public boolean claim(String claimName){
        if(this.claimName.contains("Unclaimed")) {
            this.claimName = claimName;
            claim.setText("Claimed By: " + claimName);
            return true;
        }
        return false;
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

    public ArrayList<Resource> getSpecificResources(){
        return specificResources;
    }

    @Override
    public void untarget() {
        isTarget = false;
    }

    @Override
    public Point2D getPosition(){
        return new Point2D(translate.getX(), translate.getY());

    }
}
