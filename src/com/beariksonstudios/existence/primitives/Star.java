package com.beariksonstudios.existence.primitives;

import javafx.geometry.Point2D;

/**
 * Created by Neal on 7/25/2015.
 */
public class Star extends Polygon {
    public Star() {
        super(-3, 4, -2, 1, -4, -1, -1, -1, 0, -4, 1, -1, 4, -1, 2, 1, 3, 4, 0, 2, -3, 4);
    }

    @Override
    public Point2D getCentroid() {
        double[] xPoints = getXPoints();
        double[] yPoints = getYPoints();

        return new Point2D((xPoints[3] + xPoints[7] + xPoints[10]) / 3d,
                (yPoints[3] + yPoints[7] + yPoints[10]) / 3d);
    }

    @Override
    public void setCenter(Point2D centerPos) {
        setTranslation(centerPos.getX(),
                centerPos.getY());
        update();
    }
}
