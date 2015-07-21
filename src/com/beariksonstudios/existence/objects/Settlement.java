package com.beariksonstudios.existence.objects;

import com.beariksonstudios.existence.Game;
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
    Game game;
    private double initialPopulation;
    private double popNumber;
    private SettlementType type;
    private double previousCalc;
    private double lastChangeYear;
    private double currentGrowthRate;
    private Affine transform;
    private Scale scale;
    private Rotate rotate;
    private Translate translate;
    private double startYear;
    private double yearsFromBuild;

    public Settlement(Game game, double initialPopulation, double x, double y) {
        this.game = game;
        this.initialPopulation = initialPopulation;
        popNumber = 0d;
        previousCalc = 0d;
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
        yearsFromBuild = game.getYearsFromStart() - startYear;
        double currentCalc = initialPopulation * Math.pow(Math.E, (getRate() * (yearsFromBuild - lastChangeYear)));
        double populationDiff = currentCalc - previousCalc;
        if (populationDiff > 0) {
            previousCalc = currentCalc;
            popNumber += populationDiff;
        }
        this.checkType();
        gc.setFill(Color.GAINSBORO);
        type.render(popNumber, gc, transform);
    }

    public double getPopulation() {
        return popNumber;
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

    public double getRate() {
        return type.getGrowthRate();
    }

    public void changeGrowthRate(double newGrowthRate) {
        currentGrowthRate = newGrowthRate;
        lastChangeYear = game.getYearsFromStart();
        initialPopulation = popNumber;
        previousCalc = initialPopulation * Math.pow(Math.E, (getRate() * (yearsFromBuild - lastChangeYear)));
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
