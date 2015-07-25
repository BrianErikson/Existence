package com.beariksonstudios.existence.scenes.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Translate;

/**
 * Created by BrianErikson on 7/25/2015.
 */
public class CanvasKeyEventHandler implements EventHandler<KeyEvent> {
    final Game game;

    public CanvasKeyEventHandler(Game game) {this.game = game;}

    @Override
    public void handle(KeyEvent event) {
        Translate cameraTranslation = game.getCameraTransform();
        
        switch (event.getCode()) {
            case W:
                cameraTranslation.setY(cameraTranslation.getY() + Game.MOVE_SPEED);
                break;
            case S:
                cameraTranslation.setY(cameraTranslation.getY() - Game.MOVE_SPEED);
                break;
            case A:
                cameraTranslation.setX(cameraTranslation.getX() + Game.MOVE_SPEED);
                break;
            case D:
                cameraTranslation.setX(cameraTranslation.getX() - Game.MOVE_SPEED);
                break;
        }
    }
}
