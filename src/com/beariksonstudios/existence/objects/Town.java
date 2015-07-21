package com.beariksonstudios.existence.objects;

import com.sun.javafx.geom.transform.Affine3D;
import com.sun.javafx.geom.transform.BaseTransform;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;



/**
 * Created by Neal on 7/13/2015.
 */
public class Town implements SettlementType {
    Polygon triangle;

    public Town(){
        triangle = new Polygon(
                -1, 0,
                1, 0,
                0, -1
        );
    }
    @Override
    public String getName() {
        return "Town";
    }

    @Override
    public void render(double population, GraphicsContext gc, Affine transform) {
        double triangleScale = population * 0.01d;

        triangle.setScaleX(triangleScale);
        triangle.setScaleY(triangleScale);

        double[] xPoints = getXPoints(triangle);
        double[] yPoints = getYPoints(triangle);

        double centroidX = (xPoints[0] + xPoints[1] + xPoints[2])/3d;
        double centroidY = (yPoints[0] + yPoints[1] + yPoints[2])/3d;

        Affine localTransform = transform.clone();
        triangle.setTranslateX(localTransform.getTx()-centroidX);
        triangle.setTranslateY(localTransform.getTy() - centroidY);

        gc.setTransform(localTransform);
        gc.setFill(Color.BLACK);
        gc.fillPolygon(xPoints, yPoints, triangle.getPoints().size() / 2);
        gc.setTransform(new Affine());
    }

    @Override
    public double getGrowthRate() {
        return 0.5d;
    }

    @Override
    public String getResources() {
        return "Wheat";
    }

    @Override
    public Shape getShape() {
        return triangle;
    }

    private double[] getXPoints(Polygon gon) {
        double[] arr = new double[gon.getPoints().size()/2]; // arr = 3 index places
        ObservableList<Double> points = gon.getPoints(); // 6 index places
        for (int i  = 0; i < points.size(); i+= 2) {
            int index = i;
             if (i > 1)
                 index /= 2;

            arr[index] = points.get(i);
        }

        return arr;
    }

    private double[] getYPoints(Polygon gon) {
        double[] arr = new double[gon.getPoints().size()/2];
        ObservableList<Double> points = gon.getPoints();
        for (int i  = 1; i < points.size(); i+= 2) {
            int index = i;
            if (i > 1)
                index /= 2;

            arr[index] = points.get(i);
        }

        return arr;
    }
}
