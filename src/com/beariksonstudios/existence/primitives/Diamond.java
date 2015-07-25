package com.beariksonstudios.existence.primitives;

import javafx.geometry.Point2D;

/**
 * Created by Neal on 7/25/2015.
 */
public class Diamond extends  Polygon {
    public Diamond(){
        super(0,-1,-1,0,0,1,1,0);
    }
    @Override
    public Point2D getCentroid() {
        double[] xPoints = getXPoints();
        double[] yPoints = getYPoints();

        return new Point2D((xPoints[1] + xPoints[3]) / 2d,
                (yPoints[0] + yPoints[2]) / 2d);
    }

    @Override
    public void setCenter(Point2D centerPos) {
        //Point2D centroid = getCentroid();
        setTranslation(centerPos.getX(),
                centerPos.getY());
        update();
    }
}
