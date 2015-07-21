package com.beariksonstudios.existence.primitives;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

/**
 * Created by BrianErikson on 7/21/2015.
 */
public class Polygon extends javafx.scene.shape.Polygon {
    public Polygon(double... points) {
        super(points);
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

            arr[index] = points.get(i) * getScaleX();
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

            arr[index] = points.get(i) * getScaleY();
        }

        return arr;
    }

    /**
     * Sets the center of the polygon to the specified point in world space
     * @param centerPos
     */
    public void setCenter(Point2D centerPos) {}
}
