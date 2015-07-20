package com.beariksonstudios.existence.objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Affine;


/**
 * Created by Neal on 7/17/2015.
 */
public class City implements SettlementType {
    double diamondX[];
    double diamondY[];
    int diamondSize;
    double diamondScale;
    public City(){
        diamondX = new double[]{1,0,1,2};
        diamondY = new double[]{0,1,2,1};
        diamondSize = 4;
        diamondScale = 1;
    }
    @Override
    public String getName() {
        return "City";
    }

    @Override
    public void render(double population, GraphicsContext gc, Affine transform) {
        double[] finalX = new double[4];
        double[] finalY = new double[4];
        diamondScale = population * 0.01d;
        for(int i = 0; i < diamondX.length; i++){
            finalX[i] = diamondX[i] * diamondScale;
            finalY[i] = diamondY[i] * diamondScale;
        }
        double centerX = (finalX[1] + finalX[3])/2d;
        double centerY = (finalY[0] + finalY[2])/2d;
        Affine localTransform = transform.clone();
        localTransform.setTx(localTransform.getTx()-centerX);
        localTransform.setTy(localTransform.getTy()-centerY);
        gc.setTransform(localTransform);
        gc.setFill(Color.YELLOW);
        gc.fillPolygon(finalX, finalY, diamondSize);
        gc.setTransform(new Affine());
    }

    @Override
    public double getGrowthRate() {
        return 0.5d;
    }

    @Override
    public String getResources() {
        return "HoloLens/Oculus Rifts";
    }

    @Override
    public Shape getShape() {
        return null;
    }
}
