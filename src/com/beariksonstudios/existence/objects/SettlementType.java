package com.beariksonstudios.existence.objects;

import com.sun.javafx.geom.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;

import java.awt.*;

/**
 * Created by Neal on 7/7/2015.
 */
public interface SettlementType {
    public String getName();
    public void render(double population, GraphicsContext gc, Affine transform);
    public double getGrowthRate();
    public String getResources();
    public Shape getShape();
}

