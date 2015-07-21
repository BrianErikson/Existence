package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;

/**
 * Created by Neal on 7/7/2015.
 */
public class Village implements SettlementType {
    Rectangle rectangle = new Rectangle();

    @Override
    public String getName() {
        return "Village";
    }

    @Override
    public void render(double population, GraphicsContext gc, Affine transform) {
        double size = 20 + population / 50;
        rectangle.setWidth(size);
        rectangle.setHeight(size);
        rectangle.setX(transform.getTx() - rectangle.getWidth() / 2d);
        rectangle.setY(transform.getTy() - rectangle.getHeight() / 2d);
        gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public double getGrowthRate() {
        return 0.5d;
    }

    @Override
    public String getResources() {
        return "Water";
    }

    @Override
    public Shape getShape() {
        return rectangle;
    }

}
