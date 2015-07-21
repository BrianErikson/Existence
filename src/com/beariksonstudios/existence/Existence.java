package com.beariksonstudios.existence;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Neal on 7/3/2015.
 */
public class Existence extends Application {
    private static Existence singleton;
    private Stage stage;

    public Existence() {
        singleton = this;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Existence fetch() {
        if (singleton == null)
            singleton = new Existence();
        return singleton;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        primaryStage.setTitle("Existence");
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        StackPane root = new StackPane();
        primaryStage.setScene(new MainMenu(root));
        primaryStage.show();
    }

    public Stage getStage() {
        return stage;
    }
}
