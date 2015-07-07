package com.beariksonstudios.existence.objects;

import com.beariksonstudios.existence.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Neal on 7/6/2015.
 */
public class Settlement {
    private double initialPopulation;
    private double rate;
    private double popNumber;
    private SettlementType type;
    Game game;
    public Settlement(Game game, double initialPopulation) {
        this.game= game;
        this.initialPopulation = initialPopulation;
        rate = 0.7;
        type = new Village();

    }
    public void render(GraphicsContext gc){
        popNumber = initialPopulation * Math.pow(Math.E, (rate * game.getYearsFromStart()));
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
}
