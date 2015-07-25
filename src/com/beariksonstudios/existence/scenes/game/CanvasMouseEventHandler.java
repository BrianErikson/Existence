package com.beariksonstudios.existence.scenes.game;

import com.beariksonstudios.existence.gameobjects.settlement.Settlement;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

/**
 * Created by BrianErikson on 7/21/2015.
 */
public class CanvasMouseEventHandler implements EventHandler<MouseEvent> {
    final Game game;

    public CanvasMouseEventHandler(Game game) {this.game = game;}

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Clicked!");
        boolean hit = false;
        for (Settlement settlement : game.getSettlements()) {
            Shape shape = settlement.getShape();
            hit = shape.contains(event.getX(), event.getY());
            if (hit) {
                game.setTarget(settlement);

                return;
            }
        }

        if (!hit) {
            game.promptName(event.getX(), event.getY());

        }
    }
}
