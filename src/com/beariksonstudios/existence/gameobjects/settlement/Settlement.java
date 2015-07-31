package com.beariksonstudios.existence.gameobjects.settlement;

import com.beariksonstudios.existence.gameobjects.settlement.types.City;
import com.beariksonstudios.existence.gameobjects.settlement.types.Metropolis;
import com.beariksonstudios.existence.gameobjects.settlement.types.Town;
import com.beariksonstudios.existence.gameobjects.settlement.types.Village;
import com.beariksonstudios.existence.scenes.Transform;
import com.beariksonstudios.existence.scenes.game.Game;
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

    private String name;


    public Settlement(Game game, double initialPopulation, double x, double y, String name) {
        this.game = game;
        this.name = name;
        this.initialPopulation = initialPopulation;

        currentPopulation = initialPopulation;
        lastPopCalc = currentPopulation;
        startYear = game.getYearsFromStart();

        type = new Village();
        setGrowthRate(type.getGrowthRate());

        scale = new Scale();
        rotate = new Rotate();
        translate = new Translate();
        translate.setX(x);
        translate.setY(y);

        this.updateTransform();
    }

    public void render(GraphicsContext gc, Transform camera) {
        age = game.getYearsFromStart() - startYear;

        double currentPopCalc = calculatePopulation();
        double populationDiff = currentPopCalc - lastPopCalc;
        if (populationDiff > 0) {
            lastPopCalc = currentPopCalc;
            currentPopulation += populationDiff;
        }

        checkSettlementType();

        gc.setFill(Color.GAINSBORO);

        Affine settlementTransform = transform.clone();
        settlementTransform.append(camera.get());
        type.render(currentPopulation, gc, settlementTransform);
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
                type = new Metropolis();
                this.setGrowthRate(type.getGrowthRate());
            }
        } else if (this.getPopulation() >= 15000) {
            if (!(type instanceof City)) {
                type = new City();
                this.setGrowthRate(type.getGrowthRate());
            }
        } else if (this.getPopulation() >= 10000) {
            if (!(type instanceof Town)) {
                type = new Town();
                this.setGrowthRate(type.getGrowthRate());
            }
        }
        else{
            if(!(type instanceof Village)){
                type = new Village();
                this.setGrowthRate((type.getGrowthRate()));
            }
        }
    }

    public double getCurrentGrowthRate() {
        return currentGrowthRate;
    }

    public void setGrowthRate(double newGrowthRate) {
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

    public double calculatePopulation() {
        return initialPopulation * Math.pow(Math.E, (getCurrentGrowthRate() * (age - lastGrowthChange)));
    }

    public Shape getShape() {
        return type.getShape();
    }

    public String getName() {
        return name;
    }

    public void addPopulation(int population) {
        currentPopulation += population;
        setGrowthRate(getCurrentGrowthRate());
    }
}
