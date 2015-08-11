package com.beariksonstudios.existence.gameobjects.settlement;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.beariksonstudios.existence.gameobjects.ClickableObject;
import com.beariksonstudios.existence.gameobjects.settlement.types.City;
import com.beariksonstudios.existence.gameobjects.settlement.types.Metropolis;
import com.beariksonstudios.existence.gameobjects.settlement.types.Town;
import com.beariksonstudios.existence.gameobjects.settlement.types.Village;
import com.beariksonstudios.existence.resources.Resource;
import com.beariksonstudios.existence.resources.map.MapResource;
import com.beariksonstudios.existence.scenes.game.Game;
import com.kotcrab.vis.ui.widget.VisLabel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Neal on 7/6/2015.
 */
public class Settlement extends Image implements ClickableObject {
    private Game game;
    private double initialPopulation;
    private double currentPopulation;
    private double lastPopCalc;

    private double startYear;
    private double age;
    private double lastGrowthChange; // last year in which the growth changed

    private SettlementType type;
    private float currentGrowthRate;

    private String name;
    private Vector2 position;

    private VisLabel popLabel;
    private VisLabel resourceLabel;
    private VisLabel yearLabel;
    private VisLabel typeLabel;
    private VisLabel settlementName;
    private ArrayList<VisLabel> labels = new ArrayList<VisLabel>();
    private ArrayList<MapResource> claimedResources = new ArrayList<MapResource>();

    private boolean isTarget = false;
    private String resourceListing = "";

    public Settlement(Game game, long initialPopulation, float x, float y, String name) {
        this.game = game;
        this.name = name;
        this.initialPopulation = initialPopulation;

        currentPopulation = initialPopulation;
        lastPopCalc = currentPopulation;
        startYear = game.getYearsFromStart();

        type = new Village(this);
        setupSettlementType();

        position = new Vector2(x,y);
        setPosition(position.x - getImageWidth() / 2f, position.y - getImageHeight() / 2f);

        popLabel = new VisLabel("Population: " + "No current Settlements in this Kingdom me Lord");
        resourceLabel = new VisLabel("Resource: " + "Me Lord! We have no resources!");
        typeLabel = new VisLabel("Settlement Type: " + "Your people wander and suffer aimlessly");
        settlementName = new VisLabel("Settlement Name: " + getName());
        labels.add(popLabel);
        labels.add(resourceLabel);
        labels.add(typeLabel);
        labels.add(settlementName);

        for (MapResource resource : game.getMapResources()) {
            Vector2 resourcePos = resource.getPosition();
            float distance = resourcePos.dst(new Vector2(getX(), getY()));
            if(distance < 600){
                boolean successful = resource.claim(this.name);
                if(successful){
                    this.addClaimedResource(resource);
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        setPosition(position.x - getImageWidth() / 2f, position.y - getImageHeight() / 2f);
        update();
    }

    public void update() {
        double newAge = game.getYearsFromStart() - startYear;
        if(age < newAge && Math.floor(age) != Math.floor(newAge)){
            System.out.println("is in");
            decrementClaimedResources();
        }
        age = newAge;

        double currentPopCalc = calculatePopulation();
        double populationDiff = currentPopCalc - lastPopCalc;
        if (populationDiff > 0) {
            lastPopCalc = currentPopCalc;
            currentPopulation += populationDiff;
        }

        checkSettlementType();

        checkSettlementType();

        type.update(currentPopulation);
        updateUI();
    }

    private void decrementClaimedResources() {
        double amount = (currentPopulation * 0.0001);
        System.out.println(amount);
        System.out.println(claimedResources.size());
        for (MapResource claimedResource : claimedResources) {
            claimedResource.subtractQuantity(amount);
        }

    }

    public void updateUI() {
        if (isTarget) {
            popLabel.setText("Population: " + Math.floor(getPopulation()));
            typeLabel.setText("Settlement Type: " + getType());
            resourceLabel.setText("Resources: " + getResources());
        }
    }

    public double getPopulation() {
        return currentPopulation;
    }

    public String getType() {
        return type.getName();
    }

    public String getResources() {
        return resourceListing;
    }

    private void checkSettlementType() {
        if (this.getPopulation() >= 1000000) {
            if (!(type instanceof Metropolis)) {
                type = new Metropolis(this);
                setupSettlementType();
            }
        } else if (this.getPopulation() >= 100000) {
            if (!(type instanceof City)) {
                type = new City(this);
                setupSettlementType();
            }
        } else if (this.getPopulation() >= 10000) {
            if (!(type instanceof Town)) {
                type = new Town(this);
                setupSettlementType();
            }
        }
        else{
            if(!(type instanceof Village)){
                type = new Village(this);
                setupSettlementType();
            }
        }
    }

    private void setupSettlementType() {
        this.setGrowthRate((type.getGrowthRate() + currentGrowthRate));
        type.filterResources(this.claimedResources);
        resourceListing = type.getResources();
    }

    public float getCurrentGrowthRate() {
        return currentGrowthRate;
    }

    public void setGrowthRate(float newGrowthRate) {
        currentGrowthRate = newGrowthRate;
        System.out.println("Current Growth rate set to: " + currentGrowthRate);
        lastGrowthChange = game.getYearsFromStart();
        initialPopulation = currentPopulation;
        lastPopCalc = initialPopulation * Math.pow(Math.E, (getCurrentGrowthRate() * (age - lastGrowthChange)));

    }

    public double calculatePopulation() {
        return initialPopulation * Math.pow(Math.E, (getCurrentGrowthRate() * (age - lastGrowthChange)));
    }

    public Texture getTexture() {
        return type.getTexture();
    }

    public void addPopulation(int population) {
        currentPopulation += population;
        setGrowthRate(getCurrentGrowthRate());
    }

    public ArrayList<MapResource> getClaimedResources(){
        return claimedResources;
    }

    public void addClaimedResource(MapResource resource) {
        claimedResources.add(resource);
        adjustGrowthRate(resource);
        type.filterResources(this.claimedResources);
        resourceListing = type.getResources();
    }

    public void addClaimedResource(MapResource... resources){
        Collections.addAll(claimedResources, resources);
    }

    public void adjustGrowthRate(MapResource resource){
        System.out.println("adjustGrowthRate");
        float newGrowthRate = 0;

        for (Resource specificResource : resource.getSpecificResources()) {
            switch(specificResource) {
                case GRAIN:
                    newGrowthRate += 0.3d;
                    break;
                case FRUIT:
                    newGrowthRate += 0.3d;
                    break;
                case HIGH_FRUCTOSE_CORN_SYRUP:
                    newGrowthRate += 0.3d;
                    break;
                case ORGANIC_VEGITABLES:
                    newGrowthRate += 0.3d;
                    break;
                case WATER:
                    newGrowthRate += 0.3d;
                    break;
                case FISH:
                    newGrowthRate += 0.3d;
                    break;
                case CRAB:
                    newGrowthRate += 0.3d;
                    break;
                case HYDROELECTRIC_PLANT:
                    newGrowthRate += 0.3d;
                    break;
                case STONE:
                    newGrowthRate += 0.3d;
                    break;
                case IRON:
                    newGrowthRate += 0.3d;
                    break;
                case GOLD:
                    newGrowthRate += 0.3d;
                    break;
                case URANIUM:
                    newGrowthRate += 0.3d;
                    break;
                case WOOD:
                    newGrowthRate += 0.3d;
                    break;
                case VENISON:
                    newGrowthRate += 0.3d;
                    break;
                case PAPER:
                    newGrowthRate += 0.3d;
                    break;
                case OIL:
                    newGrowthRate += 0.3d;
                    break;
            }
        }

        setGrowthRate(getCurrentGrowthRate() + newGrowthRate);
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
    @Override
    public void untarget() {
        isTarget = false;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }
}
