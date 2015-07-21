package com.beariksonstudios.existence.gameobjects.settlement;

import com.beariksonstudios.existence.scenes.Game;
import com.beariksonstudios.existence.gameobjects.settlement.types.City;
import com.beariksonstudios.existence.gameobjects.settlement.types.Metropolis;
import com.beariksonstudios.existence.gameobjects.settlement.types.Town;
import com.beariksonstudios.existence.gameobjects.settlement.types.Village;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * Created by Neal on 7/6/2015.
 */
public class Settlement {
    private Game game;
    private double initialPopulation;
    private double currentPopulation;
    private double lastPopCalc;

    private double startYear;
    private double age;
    private double lastGrowthChange; // last year in which the growth changed

    private SettlementType type;
    private double currentGrowthRate;
    
    private Affine transform;
    private Scale scale;
    private Rotate rotate;
    private Translate translate;


    public Settlement(Game game, double initialPopulation, double x, double y) {
        this.game = game;
        this.initialPopulation = initialPopulation;
        currentPopulation = 0d;
        lastPopCalc = 0d;
        type = new Village();
        scale = new Scale();
        rotate = new Rotate();
        translate = new Translate();
        translate.setX(x);
        translate.setY(y);
        startYear = game.getYearsFromStart();
        this.updateTransform();

    }

    public void render(GraphicsContext gc) {
        age = game.getYearsFromStart() - startYear;
        double currentPopCalc = initialPopulation * Math.pow(Math.E, (getCurrentGrowthRate() * (age - lastGrowthChange)));
        double populationDiff = currentPopCalc - lastPopCalc;
        if (populationDiff > 0) {
            lastPopCalc = currentPopCalc;
            currentPopulation += populationDiff;
        }
        this.checkType();
        gc.setFill(Color.GAINSBORO);
        type.render(currentPopulation, gc, transform);
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

    public void checkType() {
        if (this.getPopulation() > 20000) {
            if (!(type instanceof Metropolis)) {
                type = new Metropolis();
            }
        } else if (this.getPopulation() > 15000) {
            if (!(type instanceof City)) {
                type = new City();
                this.changeGrowthRate(type.getGrowthRate());
            }
        } else if (this.getPopulation() > 10000) {
            if (!(type instanceof Town)) {
                type = new Town();
                this.changeGrowthRate(type.getGrowthRate());
            }
        }
    }

    public double getCurrentGrowthRate() {
        return currentGrowthRate;
    }

    public void changeGrowthRate(double newGrowthRate) {
        currentGrowthRate = newGrowthRate;
        lastGrowthChange = game.getYearsFromStart();
        initialPopulation = currentPopulation;
        lastPopCalc = initialPopulation * Math.pow(Math.E, (getCurrentGrowthRate() * (age - lastGrowthChange)));
    }

    public void updateTransform() {
        transform = new Affine();
        transform.append(scale);
        transform.append(rotate);
        transform.append(translate);
    }

    public Shape getShape() {
        return type.getShape();
    }
}
