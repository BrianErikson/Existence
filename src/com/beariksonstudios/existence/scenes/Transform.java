package com.beariksonstudios.existence.scenes;

import javafx.geometry.Point2D;
import javafx.scene.transform.*;

/**
 * Created by BrianErikson on 7/31/2015.
 */
public class Transform {
    private Scale scale;
    private Rotate rotate;
    private Translate translation;
    private Affine affine;
    private boolean isDirty = false;

    public Transform() {
        scale = new Scale();
        rotate = new Rotate();
        translation = new Translate();
        affine = new Affine();
        isDirty = true;
    }

    public void update() {
        updateTransform();
    }

    private void updateTransform() {
        if (isDirty) {
            affine.setToIdentity();
            affine.append(scale);
            affine.append(rotate);
            affine.append(translation);
            System.out.println("Translation mat: " + translation.toString());
            System.out.println("Affine pos: " + getPosition().toString());
            isDirty = false;
        }
    }

    public double getX() {
        return affine.getTx();
    }

    public double getY() {
        return affine.getTy();
    }

    public Point2D getPosition() {
        return new Point2D(affine.getTx(), affine.getTy());
    }

    public void setPosition(Point2D point) {
        translation.setX(-point.getX()); // TODO SUPER HACKY
        translation.setY(-point.getY()); // TODO SUPER HACKY
        isDirty = true;
    }

    public void translate(Point2D offset) {
        translation.setX(translation.getX() + offset.getX());
        translation.setY(translation.getY() + offset.getY());
        isDirty = true;
    }

    public void setRotation(int angle) {
        rotate.setAngle(angle);
        isDirty = true;
    }

    public void setScale(Point2D p) {
        scale.setX(p.getX());
        scale.setY(p.getY());
        isDirty = true;
    }

    public Point2D project(Point2D point) {
        return affine.transform(point);
    }

    public Point2D project(double x, double y) {
        return affine.transform(x, y);
    }

    public Point2D deproject(Point2D point) throws NonInvertibleTransformException {
        return affine.inverseTransform(point);
    }

    public Affine get() {
        return affine;
    }

    @Override
    public String toString() {
        return affine.toString();
    }
}
