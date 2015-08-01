package com.beariksonstudios.existence.primitives;

import javafx.geometry.Point2D;

/**
 * Created by BrianErikson on 7/21/2015.
 */
public class Triangle extends Polygon {
    public Triangle() {
        super(-1, 0,
                1, 0,
                0, -1);
    }

    @Override
    public Point2D getCentroid() {
        double[] xPoints = getXPoints();
        double[] yPoints = getYPoints();

        return new Point2D((xPoints[0] + xPoints[1] + xPoints[2]) / 3d,
                (yPoints[0] + yPoints[1] + yPoints[2]) / 3d);
    }

    @Override
    public void setCenter(Point2D centerPos) {
        //Point2D centroid = getCentroid();
        setTranslation(centerPos.getX(),
                centerPos.getY());
        update();
    }
}
