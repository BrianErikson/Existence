package com.beariksonstudios.existence.objects;

import com.beariksonstudios.existence.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Neal on 7/6/2015.
 */
public class Settlement {
    private double initialPopulation;
    private double popNumber;
    private SettlementType type;
    private double previousCalc;
    private double lastChangeYear;
    private double currentGrowthRate;
    Game game;
    public Settlement(Game game, double initialPopulation) {
        this.game= game;
        this.initialPopulation = initialPopulation;
        popNumber = 0d;
        previousCalc = 0d;
        type = new Village();

    }
    public void render(GraphicsContext gc){
        double currentCalc = initialPopulation * Math.pow(Math.E, (getRate() * (game.getYearsFromStart()-lastChangeYear)));
        System.out.println(game.getYearsFromStart()-lastChangeYear);
        double populationDiff = currentCalc - previousCalc;
        if(populationDiff > 0){
            previousCalc = currentCalc;
            popNumber += populationDiff;
        }
        this.checkType();
        gc.setFill(Color.GAINSBORO);
        type.render(popNumber, gc);
    }
    public double getPopulation() {
        return popNumber;
    }
    public String getType(){
        return type.getName();
    }
    public String getResources(){
        return type.getResources();
    }
    public void checkType(){
        if(this.getPopulation() > 20000){
            if(!(type instanceof Metropolis)) {
                type = new Metropolis();
            }
        }
        else if(this.getPopulation() > 15000) {
            if (!(type instanceof City)) {
                type = new City();
                this.changeGrowthRate(type.getGrowthRate());
            }
        }
        else if(this.getPopulation() > 10000){
            if(!(type instanceof Town)) {
                type = new Town();
                this.changeGrowthRate(type.getGrowthRate());
            }
        }
    }
    public double getRate(){
        return type.getGrowthRate();
    }
    public void changeGrowthRate(double newGrowthRate){
        currentGrowthRate = newGrowthRate;
        lastChangeYear = game.getYearsFromStart();
        initialPopulation = popNumber;
        previousCalc =  initialPopulation * Math.pow(Math.E, (getRate() * (game.getYearsFromStart()-lastChangeYear)));
    }
}
