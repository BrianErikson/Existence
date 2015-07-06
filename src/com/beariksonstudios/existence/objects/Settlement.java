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
    Game game;
    public Settlement(Game game, double initialPopulation) {
        this.game= game;
        this.initialPopulation = initialPopulation;
        rate = 0.7;

    }
    public void render(GraphicsContext gc){
        popNumber = initialPopulation * Math.pow(Math.E, (rate * game.getYearsFromStart()));
        gc.setFill(Color.GAINSBORO);
        double size = 20 + popNumber/50;
        gc.fillRect(gc.getCanvas().getWidth()/2d - size/2d, gc.getCanvas().getHeight()/2d - size/2d, size, size);
    }
    public double getPopulation() {
        return popNumber;
    }
}
