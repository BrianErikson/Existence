package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;
import com.beariksonstudios.existence.primitives.Triangle;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;


/**
 * Created by Neal on 7/13/2015.
 */
public class Town implements SettlementType {
    Triangle triangle;
    public static int MIN_SIZE = 30;
    public static int MAX_SIZE = 120;

    public Town() {
        triangle = new Triangle();
    }

    @Override
    public String getName() {
        return "Town";
    }

    @Override
    public void render(double population, GraphicsContext gc, Affine transform) {
        double size = (population/1000) + 20;
        if (size < MIN_SIZE)
            size = MIN_SIZE;
        else if (size > MAX_SIZE)
            size = MAX_SIZE;

        triangle.setScale(size);
        triangle.setCenter(new Point2D(transform.getTx(), transform.getTy()));
        gc.setFill(Color.BLACK);
        gc.fillPolygon(triangle.getXPoints(), triangle.getYPoints(), triangle.getPoints().size() / 2);
    }

    @Override
    public double getGrowthRate() {
        return 1d;
    }

    @Override
    public String getResources() {
        return "Wheat";
    }

    @Override
    public Shape getShape() {
        return triangle;
    }
}
