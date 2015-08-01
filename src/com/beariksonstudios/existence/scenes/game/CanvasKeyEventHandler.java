package com.beariksonstudios.existence.scenes.game;

import com.beariksonstudios.existence.scenes.Camera;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;

/**
 * Created by BrianErikson on 7/25/2015.
 */
public class CanvasKeyEventHandler implements EventHandler<KeyEvent> {
    final Game game;

    public CanvasKeyEventHandler(Game game) {
        this.game = game;
    }

    @Override
    public void handle(KeyEvent event) {
        Camera cameraTranslation = game.getCameraTransform();

        switch (event.getCode()) {
            case W:
                cameraTranslation.translate(new Point2D(0d, -Game.MOVE_SPEED));
                break;
            case S:
                cameraTranslation.translate(new Point2D(0d, Game.MOVE_SPEED));
                break;
            case A:
                cameraTranslation.translate(new Point2D(-Game.MOVE_SPEED, 0d));
                break;
            case D:
                cameraTranslation.translate(new Point2D(Game.MOVE_SPEED, 0d));
                break;
        }
    }
}
