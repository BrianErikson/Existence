package com.beariksonstudios.existence.gameobjects.settlement;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.beariksonstudios.existence.gameobjects.settlement.types.City;
import com.beariksonstudios.existence.gameobjects.settlement.types.Metropolis;
import com.beariksonstudios.existence.gameobjects.settlement.types.Town;
import com.beariksonstudios.existence.gameobjects.settlement.types.Village;
import com.beariksonstudios.existence.scenes.game.Game;

/**
 * Created by Neal on 7/6/2015.
 */
public class Settlement extends Image {
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

    public Settlement(Game game, long initialPopulation, float x, float y, String name) {
        this.game = game;
        this.name = name;
        this.initialPopulation = initialPopulation;

        currentPopulation = initialPopulation;
        lastPopCalc = currentPopulation;
        startYear = game.getYearsFromStart();

        type = new Village(this);
        setGrowthRate(type.getGrowthRate());
        position = new Vector2(x,y);
        setPosition(position.x - getImageWidth() / 2f, position.y - getImageHeight() / 2f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        setPosition(position.x - getImageWidth() / 2f, position.y - getImageHeight() / 2f);
        update();
    }

    public void update() {
        age = game.getYearsFromStart() - startYear;

        double currentPopCalc = calculatePopulation();
        double populationDiff = currentPopCalc - lastPopCalc;
        if (populationDiff > 0) {
            lastPopCalc = currentPopCalc;
            currentPopulation += populationDiff;
        }

        checkSettlementType();

        type.update(currentPopulation);
    }

    public double getPopulation() {
        return currentPopulation;
    }

    public String getType() {
        return type.getName();
    }

    public String getResources() {
        return type.getResources();
    }

    public void checkSettlementType() {
        if (this.getPopulation() >= 20000) {
            if (!(type instanceof Metropolis)) {
                type = new Metropolis(this);
                this.setGrowthRate(type.getGrowthRate());
            }
        } else if (this.getPopulation() >= 15000) {
            if (!(type instanceof City)) {
                type = new City(this);
                this.setGrowthRate(type.getGrowthRate());
            }
        } else if (this.getPopulation() >= 10000) {
            if (!(type instanceof Town)) {
                type = new Town(this);
                this.setGrowthRate(type.getGrowthRate());
            }
        }
        else{
            if(!(type instanceof Village)){
                type = new Village(this);
                this.setGrowthRate((type.getGrowthRate()));
            }
        }
    }

    public float getCurrentGrowthRate() {
        return currentGrowthRate;
    }

    public void setGrowthRate(float newGrowthRate) {
        currentGrowthRate = newGrowthRate;
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

    public String getName() {
        return name;
    }

    public void addPopulation(int population) {
        currentPopulation += population;
        setGrowthRate(getCurrentGrowthRate());
    }
}
