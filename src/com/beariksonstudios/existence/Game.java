package com.beariksonstudios.existence;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Neal on 7/3/2015.
 */
public class Game extends Scene {
    public static long fps = 30; // 30fps
    private Canvas canvas;
    private Label population;
    private Label resource;
    private double popNumber;
    private double startTime = System.currentTimeMillis();
    private double time;


    public Game(StackPane root) {
        super(root);
        double initialPopulation = 1000;
        double rate = 1;
        time = System.currentTimeMillis() - startTime;
        popNumber = Math.pow(initialPopulation * Math.E, rate * time);
        Stage stage = Existence.fetch().getStage();

        canvas = new Canvas(stage.getWidth(),stage.getHeight());
        root.getChildren().add(canvas);
        root.getChildren().add(createUI());

        startRenderTimer();
    }

    public BorderPane createUI() {
        BorderPane pane = new BorderPane();
        pane.setBottom(createBottomPane());

        return pane;
    }

    private VBox createBottomPane() {
        VBox labelVBox = new VBox();
        labelVBox.setStyle("-fx-background-color: gainsboro;");

        population = new Label("Population: " + popNumber);
        resource = new Label("Resource: Crocodiles");

        labelVBox.getChildren().addAll(population, resource);

        return labelVBox;
    }

    private void startRenderTimer() {
        final Timer renderTimer = new Timer("RenderTimer");
        renderTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        render(canvas.getGraphicsContext2D());
                    }
                });
            }
        }, 0l, 1000l / fps);
    }

    public void render(GraphicsContext gc){
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        time = System.currentTimeMillis() - startTime;
        this.updateUI();
    }
    public void updateUI(){
        population.setText("Population: " + Double.toString(popNumber));
    }
}
