package com.beariksonstudios.existence.objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Neal on 7/18/2015.
 */
public class Metropolis implements SettlementType {
    double starX[];
    double starY[];
    int starSize;
    double starScale;
    public Metropolis(){
        starX = new double[]{-3,-2,-4,-1,0,1,4,2,3,0,-3};
        starY = new double[]{4,1,-1,-1,-4,-1,-1,1,4,2,4};
        starSize = 11;
        starScale = 1;
    }
    @Override
    public String getName() {
        return "Metropolis";
    }

    @Override
    public void render(double population, GraphicsContext gc) {
        double[] finalX = new double[11];
        double[] finalY = new double[11];
        starScale = population * 0.001d;
        for(int i = 0; i < starX.length; i++) {
            finalX[i] = starX[i] * starScale;
            finalY[i] = starY[i] * starScale;
        }
        double centerX = (finalX[3] + finalX[7] + finalX[10])/3d;
        double centerY = (finalY[3] + finalY[7] + finalY[10])/3d;
        gc.translate(gc.getCanvas().getWidth()/2d - centerX, gc.getCanvas().getHeight()/2d - centerY);
        gc.setFill(Color.BLUE);
        gc.fillPolygon(finalX, finalY, starSize);
        gc.translate(-(gc.getCanvas().getWidth()/2d - centerX), -(gc.getCanvas().getHeight()/2d - centerY));

    }

    @Override
    public double getGrowthRate() {
        return 0.5d;
    }

    @Override
    public String getResources() {
        return "Hoverboard, Instant Obesity reducers, Robot Wives";
    }
}
