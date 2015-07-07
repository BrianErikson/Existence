package com.beariksonstudios.existence;

import com.beariksonstudios.existence.objects.Settlement;
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

import java.util.Calendar;
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
    private Label year;
    private Label type;
    private double startTime = System.currentTimeMillis();
    private double time;
    private double currentYear;
    private double initialYear;
    public static double gameSecPerYear = 10;
    private double yearsFromStart;
    Settlement settlement;


    public Game(StackPane root) {
        super(root);
        settlement = new Settlement(this, 1000);
        time = (System.currentTimeMillis() - startTime)/1000;
        initialYear = 1900;
        currentYear = initialYear + (time/gameSecPerYear);
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

        population = new Label("Population: " + settlement.getPopulation());
        resource = new Label("Resource: " + settlement.getResources());
        year = new Label("Current Year: " + initialYear);
        type = new Label("Settlement Type: " + settlement.getType());


        labelVBox.getChildren().addAll(type ,population, resource, year);

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
        currentYear = initialYear + (time/gameSecPerYear);
        yearsFromStart = currentYear - initialYear;
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        time = (System.currentTimeMillis() - startTime)/1000;
        settlement.render(gc);
        this.updateUI();
    }
    public void updateUI(){
        population.setText("Population: " + (long) settlement.getPopulation());
        year.setText("Current Year: " + Math.floor(currentYear));
        type.setText("Settlement Type: " + settlement.getType());
        resource.setText("Resources: " + settlement.getResources());

    }
    public double getYearsFromStart() {
        return yearsFromStart;
    }
}
