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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
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
    private ArrayList<Settlement> settlements = new ArrayList<Settlement>();
    public Game(StackPane root) {
        super(root);
        time = (System.currentTimeMillis() - startTime)/1000;
        initialYear = 1900;
        currentYear = initialYear + (time/gameSecPerYear);
        Stage stage = Existence.fetch().getStage();

        canvas = new Canvas(stage.getWidth(),stage.getHeight());
        root.getChildren().add(canvas);
        root.getChildren().add(createUI());
        canvas.setPickOnBounds(true);
        canvas.setFocusTraversable(true);
        canvas.addEventFilter(MouseEvent.ANY, e -> canvas.requestFocus());
        final Game game = this;
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Clicked!");
            settlements.add(new Settlement(game, 1000));
        });
        startRenderTimer();
    }

    public BorderPane createUI() {
        BorderPane pane = new BorderPane();
        pane.setBottom(createBottomPane());
        pane.setPickOnBounds(false);

        return pane;
    }

    private VBox createBottomPane() {
        VBox labelVBox = new VBox();
        labelVBox.setStyle("-fx-background-color: gainsboro;");
        population = new Label("Population: " + "No current Settlements in this Kingdom me Lord");
        resource = new Label("Resource: " + "Me Lord! We have no resources!");
        type = new Label("Settlement Type: " + "Your people wander and suffer aimlessly");
        year = new Label("Current Year: " + initialYear);


        labelVBox.getChildren().addAll(type ,population, resource, year);

        return labelVBox;
    }

    private void startRenderTimer() {
        final Timer renderTimer = new Timer("RenderTimer", true);
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
        for (Settlement settlement : settlements) {
            settlement.render(gc);
        }
        this.updateUI();
    }
    public void updateUI(){
        if(settlements.size() > 0) {
            population.setText("Population: " + (long) settlements.get(0).getPopulation());
            type.setText("Settlement Type: " + settlements.get(0).getType());
            resource.setText("Resources: " + settlements.get(0).getResources());
        }
        year.setText("Current Year: " + Math.floor(currentYear));

    }
    public double getYearsFromStart() {
        return yearsFromStart;
    }
}
