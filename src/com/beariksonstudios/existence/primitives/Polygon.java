package com.beariksonstudios.existence.primitives;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import java.util.ArrayList;

/**
 * Created by BrianErikson on 7/21/2015.
 */
public abstract class Polygon extends javafx.scene.shape.Polygon {
    ArrayList<Double> model;

    private Affine transform;
    private Scale scale;
    private Rotate rotate;
    private Translate translate;

    public Polygon(double... points) {
        super(points);
        model = new ArrayList<Double>();
        for (int i = 0; i < points.length; i++) {
            model.add(points[i]);
        }

        scale = new Scale();
        rotate = new Rotate();
        translate = new Translate();
        transform = new Affine();
        update();
    }

    public void update() {
        updateTransform();
        updatePoints();
    }

    public void updateTransform() {
        transform.setToIdentity();
        transform.prepend(scale);
        transform.prepend(rotate);
        transform.prepend(translate);
    }

    public void updatePoints() {

        System.out.println(transform.toString());
        ArrayList<Double> newPoints = new ArrayList<>();
        for (int i = 0; i < model.size(); i += 2) {
            Point2D point = new Point2D(model.get(i), model.get(i+1));
            Point2D worldPoint = transform.transform(point);
            System.out.println(worldPoint);
            newPoints.add(worldPoint.getX());
            newPoints.add(worldPoint.getY());
        }
        getPoints().setAll(newPoints);
    }

    /**
     * Returns properly scaled coordinates
     *
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
     *
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
        this.scale.setX(scale);
        this.scale.setY(scale);
        update();
    }

    /**
     * Calculates and returns the center of the polygon
     *
     * @return
     */
    abstract Point2D getCentroid();

    /**
     * Sets the center of the polygon to the specified point in world space
     *
     * @param centerPos
     */
    abstract void setCenter(Point2D centerPos);

    public void translate(double x, double y) {
        translate.setX(translate.getX() + x);
        translate.setY(translate.getY() + y);
        update();
    }

    public void setTranslation(double x, double y) {
        translate.setX(x);
        translate.setY(y);
        update();
    }
}
