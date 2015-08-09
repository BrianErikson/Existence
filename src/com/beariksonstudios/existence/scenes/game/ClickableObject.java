package com.beariksonstudios.existence.scenes.game;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

/**
 * Created by Neal on 7/31/2015.
 */
public interface ClickableObject {
    public ArrayList<Label> getLabels();

    public String getName();

    public Shape getShape();

    public void setAsTarget();

    public void untarget();

    public Point2D getPosition();
}
