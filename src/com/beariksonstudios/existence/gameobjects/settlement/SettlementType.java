package com.beariksonstudios.existence.gameobjects.settlement;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;

/**
 * Created by Neal on 7/7/2015.
 */
public interface SettlementType {
    String getName();

    void render(double population, GraphicsContext gc, Affine transform);

    double getGrowthRate();

    String getResources();

    Shape getShape();
}

