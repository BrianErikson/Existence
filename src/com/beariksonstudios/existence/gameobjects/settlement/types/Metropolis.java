package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;
import com.beariksonstudios.existence.primitives.Star;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;


/**
 * Created by Neal on 7/18/2015.
 */
public class Metropolis implements SettlementType {
    Star star;
    public Metropolis() {
        star = new Star();
    }

    @Override
    public String getName() {
        return "Metropolis";
    }

    @Override
    public void render(double population, GraphicsContext gc, Affine transform) {
        star.setScale(population * 0.001d);
        star.setCenter(new Point2D(transform.getTx(), transform.getTy()));
        gc.setFill(Color.BLUE);
        gc.fillPolygon(star.getXPoints(), star.getYPoints(), star.getPoints().size()/ 2);

    }

    @Override
    public double getGrowthRate() {
        return 0.5d;
    }

    @Override
    public String getResources() {
        return "Hoverboard, Instant Obesity reducers, Robot Wives";
    }

    @Override
    public Shape getShape() {
        return star;
    }
}
