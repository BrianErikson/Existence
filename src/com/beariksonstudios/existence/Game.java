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
import javafx.scene.shape.Shape;
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
    private Label globalPop;
    private double startTime = System.currentTimeMillis();
    private double time;
    private double currentYear;
    private double initialYear;
    public static double gameSecPerYear = 10;
    private double yearsFromStart;
    private ArrayList<Settlement> settlements = new ArrayList<Settlement>();
    private double globalPopulation;
    private Settlement target;
    public Game(StackPane root) {
        super(root);
        time = (System.currentTimeMillis() - startTime)/1000;
        initialYear = 1900;
        globalPopulation = 0d;
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
            boolean hit = false;
            for(Settlement settlement: settlements){
                Shape shape = settlement.getShape();
                hit = shape.contains(event.getX(),event.getY());
                if(hit){
                    target = settlement;

                    return;
                }
            }
            if(!hit){
                settlements.add(new Settlement(game, 1000, event.getX(), event.getY()));
                if(settlements.size() == 1){
                    target = settlements.get(0);
                }
            }
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
        globalPop = new Label("Global Population: " + "There are no homes for our women to give birth");


        labelVBox.getChildren().addAll(globalPop ,type ,population, resource, year);

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
        globalPopulation = 0d;
        for (Settlement settlement : settlements) {
            settlement.render(gc);
            globalPopulation += settlement.getPopulation();
        }
        this.updateUI();

    }
    public void updateUI(){
        if(settlements.size() > 0) {
            population.setText("Population: " + (long) target.getPopulation());
            type.setText("Settlement Type: " + target.getType());
            resource.setText("Resources: " + target.getResources());
            globalPop.setText("Global Population: " + Math.floor(globalPopulation));

        }
        year.setText("Current Year: " + Math.floor(currentYear));

    }
    public double getYearsFromStart() {
        return yearsFromStart;
    }

    public double getCurrentYear() {
        return currentYear;
    }
}
