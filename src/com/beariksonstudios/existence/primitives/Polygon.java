package com.beariksonstudios.existence.primitives;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

import java.util.ArrayList;

/**
 * Created by BrianErikson on 7/21/2015.
 */
public abstract class Polygon extends javafx.scene.shape.Polygon {
    ArrayList<Double> model;
    public Polygon(double... points) {
        super(points);
        model = new ArrayList<Double>();
        for (int i = 0; i < points.length; i++) {
            model.add(points[i]);
        }
    }
    /**
     * Returns properly scaled coordinates
     * @return
     */
    public double[] getXPoints() {
        double[] arr = new double[getPoints().size() / 2]; // arr = 3 index places
        ObservableList<Double> points = getPoints(); // 6 index places
        for (int i = 0; i < points.size(); i += 2) {
            int index = i;
            if (i > 1)
                index /= 2;

            arr[index] = points.get(i);
        }

        return arr;
    }

    /**
     * Returns properly scaled coordinates
     * @return
     */
    public double[] getYPoints() {
        double[] arr = new double[getPoints().size() / 2];
        ObservableList<Double> points = getPoints();
        for (int i = 1; i < points.size(); i += 2) {
            int index = i;
            if (i > 1)
                index /= 2;

            arr[index] = points.get(i);
        }

        return arr;
    }

    public void setScale(double scale) {
        ArrayList<Double> scaledModel = new ArrayList<>();
        for(int i = 0; i < model.size();i++ ){
            scaledModel.add(model.get(i) * scale);
        }
        getPoints().setAll(scaledModel);
    }

    /**
     * Calculates and returns the center of the polygon
     * @return
     */
    abstract Point2D getCentroid();

    /**
     * Sets the center of the polygon to the specified point in world space
     * @param centerPos
     */
    abstract void setCenter(Point2D centerPos);


}
