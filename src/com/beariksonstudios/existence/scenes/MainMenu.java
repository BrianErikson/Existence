package com.beariksonstudios.existence.scenes;

import com.beariksonstudios.existence.Existence;
import com.beariksonstudios.existence.scenes.game.Game;
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

        newGame.setOnAction(event -> {
            Stage stage = Existence.fetch().getStage();

            StackPane root1 = new StackPane();
            Scene gameScene = new Game(root1);
            stage.setScene(gameScene);

            stage.show();
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
