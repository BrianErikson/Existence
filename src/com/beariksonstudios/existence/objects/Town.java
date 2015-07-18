package com.beariksonstudios.existence.objects;

import com.sun.javafx.geom.transform.Affine3D;
import com.sun.javafx.geom.transform.BaseTransform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;

/**
 * Created by Neal on 7/13/2015.
 */
public class Town implements SettlementType {
    double[] triangleX;
    double[] triangleY;
    int triangleSize;
    double triangleScale;
    public Town(){
        triangleX = new double[]{-1, 1, 0};
        triangleY = new double[]{0, 0, -1};
        triangleSize = 3;
        triangleScale = 1;

    }
    @Override
    public String getName() {
        return "Town";
    }

    @Override
    public void render(double population, GraphicsContext gc) {
        double[] finalX = new double[3];
        double[] finalY = new double[3];
        triangleScale = population * 0.01d;
        for (int i = 0; i < triangleX.length; i++) {
            finalY[i] = triangleY[i] * triangleScale;
            finalX[i] = triangleX[i] * triangleScale;
        }
        double centroidX = (finalX[0] + finalX[1] + finalX[2])/3d;
        double centroidY = (finalY[0] + finalY[1] + finalY[2])/3d;
        gc.translate(gc.getCanvas().getWidth()/2d - centroidX, gc.getCanvas().getHeight()/2d - centroidY);
        gc.setFill(Color.BLACK);
        gc.fillPolygon(finalX, finalY, triangleSize);
        gc.translate(-(gc.getCanvas().getWidth()/2d - centroidX), -(gc.getCanvas().getHeight()/2d - centroidY));

    }

    @Override
    public double getGrowthRate() {
        return 0.5d;
    }

    @Override
    public String getResources() {
        return "Wheat";
    }

}
