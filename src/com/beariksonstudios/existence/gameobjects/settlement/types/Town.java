package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.beariksonstudios.existence.primitives.Triangle;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;
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

    public Town() {
        triangle = new Triangle();
    }

    @Override
    public String getName() {
        return "Town";
    }

    @Override
    public void render(double population, GraphicsContext gc, Affine transform) {
        double triangleScale = population * 0.01d;

        triangle.setScale(triangleScale);

        Affine localTransform = transform.clone();
        triangle.setCenter(new Point2D(localTransform.getTx(), localTransform.getTy()));

        gc.setFill(Color.BLACK);
        gc.fillPolygon(triangle.getXPoints(), triangle.getYPoints(), triangle.getPoints().size() / 2);
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
}
