package com.beariksonstudios.existence;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Neal on 7/3/2015.
 */
public class MainMenu extends Scene {

    public MainMenu(StackPane root) {
        super(root);
        VBox box = new VBox();
        Button newGame = new Button("New Game");
        box.getChildren().add(newGame);
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = Existence.fetch().getStage();

                StackPane root = new StackPane();
                Scene gameScene = new Game(root);
                stage.setScene(gameScene);

                stage.show();
            }
        });
        Button loadGame = new Button("Load Game");
        Button options = new Button("Options");
        Button exit = new Button("Exit");
        box.getChildren().add(loadGame);
        box.getChildren().add(options);
        box.getChildren().add(exit);
        box.setAlignment(Pos.CENTER);
        root.getChildren().add(box);

    }
}
