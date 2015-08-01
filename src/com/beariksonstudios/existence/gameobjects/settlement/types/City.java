package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;
import com.beariksonstudios.existence.primitives.Diamond;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;


/**
 * Created by Neal on 7/17/2015.
 */
public class City implements SettlementType {
    Diamond diamond;
    public static int MIN_SIZE = 30;
    public static int MAX_SIZE = 120;
    public City() {
        diamond = new Diamond();
    }

    @Override
    public String getName() {
        return "City";
    }

    @Override
    public void render(double population, GraphicsContext gc, Affine transform) {
        double size = (population/10000) + 20;
        if (size < MIN_SIZE)
            size = MIN_SIZE;
        else if (size > MAX_SIZE)
            size = MAX_SIZE;

        diamond.setScale(size);
        diamond.setCenter(new Point2D(transform.getTx(), transform.getTy()));
        gc.setFill(Color.YELLOW);
        gc.fillPolygon(diamond.getXPoints(), diamond.getYPoints(), diamond.getPoints().size() / 2);
    }

    @Override
    public double getGrowthRate() {
        return 1d;
    }

    @Override
    public String getResources() {
        return "HoloLens/Oculus Rifts";
    }

    @Override
    public Shape getShape() {
        return diamond;
    }
}
