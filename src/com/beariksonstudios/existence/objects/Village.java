package com.beariksonstudios.existence.objects;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Neal on 7/7/2015.
 */
public class Village implements SettlementType{
    @Override
    public String getName() {
        return "Village";
    }

    @Override
    public void render(double population, GraphicsContext gc) {
        double size = 20 + population/50;
        gc.fillRect(gc.getCanvas().getWidth()/2d - size/2d, gc.getCanvas().getHeight()/2d - size/2d, size, size);
    }

    @Override
    public double getGrowthRate() {
        return 0.015d;
    }

    @Override
    public String getResources() {
        return "Water";
    }
}
