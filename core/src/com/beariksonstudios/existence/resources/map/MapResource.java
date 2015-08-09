package com.beariksonstudios.existence.resources.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.beariksonstudios.existence.gameobjects.ClickableObject;
import com.beariksonstudios.existence.resources.Resource;
import com.beariksonstudios.existence.scenes.game.Assets;
import com.kotcrab.vis.ui.widget.VisLabel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Neal on 7/26/2015.
 */
public abstract class MapResource extends Image implements ClickableObject {
    private final String name;
    private double quantity;
    private boolean isTarget = false;

    private VisLabel type;
    private VisLabel quantityLabel;
    private VisLabel claim;
    private VisLabel resource;
    private ArrayList<VisLabel> labels = new ArrayList<VisLabel>();
    private String claimName = "Unclaimed";
    private ArrayList<Resource> specificResources = new ArrayList<Resource>();

    public MapResource(String name, String textureName, int quantity, Resource... specificResources){
        this(name, textureName, quantity, 0, 0, specificResources);
    }

    public MapResource(String name, String textureName, int quantity, float x, float y,  Resource... specificResources){
        super(Assets.manager.get("map_resources/" + textureName, Texture.class));
        this.name = name;
        this.quantity = quantity;
        setPosition(x, y);

        Collections.addAll(this.specificResources, specificResources);
        type = new VisLabel("Resource Type: " + name);
        quantityLabel = new VisLabel("Amount: " + quantity);
        claim = new VisLabel("Claimed By: " + claimName);

        String resources = "";
        for (Resource resource : this.specificResources) {
            resources += resource.toString();
            resources += " ";
        }

        resource = new VisLabel("Resources Available: " + resources);

        labels.add(type);
        labels.add(quantityLabel);
        labels.add(resource);
        labels.add(claim);
    }

    public double getQuantity() {
        return quantity;
    }

    /**
     * subtracts the given amount from quantity
     * @param amount to subtract
     * @return returns true if successful, otherwise false
     *
     */
    public boolean subtractQuantity(double amount){
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
        updateUI();
    }

    public void updateUI() {
        if (isTarget) {
            DecimalFormat df = new DecimalFormat("#.00");
            quantityLabel.setText("Amount: " + df.format(quantity));
        }
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
    public ArrayList<VisLabel> getLabels() {
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
    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }
}
