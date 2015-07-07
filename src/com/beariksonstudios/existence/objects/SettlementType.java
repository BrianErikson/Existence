package com.beariksonstudios.existence.objects;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

/**
 * Created by Neal on 7/7/2015.
 */
public interface SettlementType {
    public String getName();
    public void render(double population, GraphicsContext gc);
    public double getGrowthRate();
    public String getResources();
}

