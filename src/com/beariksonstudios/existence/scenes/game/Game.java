package com.beariksonstudios.existence.scenes.game;

import com.beariksonstudios.existence.Existence;
import com.beariksonstudios.existence.gameobjects.settlement.Settlement;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Neal on 7/3/2015.
 */
public class Game extends Scene {
    public static long MS_PER_SEC = 1000;
    public static long FPS = 30;
    public static double SECS_PER_YEAR = 10; // seconds (in real-time) per game-year

    private Canvas canvas;
    private String userName;

    private Label popLabel;
    private Label resourceLabel;
    private Label yearLabel;
    private Label typeLabel;
    private Label globalPopLabel;
    private Label settlementName;

    private double startTime = System.currentTimeMillis();
    private double secsSinceStart;

    private double currentYear;
    private double initialYear;
    private double yearsFromStart;

    private double globalPopulation;
    private ArrayList<Settlement> settlements = new ArrayList<Settlement>();
    private Settlement target;

    private Translate cameraTranslation;
    public static int MOVE_SPEED = 10;

    public Game(StackPane root) {
        super(root);
        Stage stage = Existence.fetch().getStage();

        secsSinceStart = (System.currentTimeMillis() - startTime) / MS_PER_SEC;
        initialYear = 1900;
        currentYear = initialYear + (secsSinceStart / SECS_PER_YEAR);

        globalPopulation = 0d;

        canvas = new Canvas(stage.getWidth(), stage.getHeight());
        canvas.setPickOnBounds(true);
        canvas.setFocusTraversable(true);

        canvas.addEventFilter(MouseEvent.ANY, e -> canvas.requestFocus());
        canvas.addEventFilter(KeyEvent.ANY, e -> canvas.requestFocus());

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new CanvasMouseEventHandler(this));
        canvas.addEventHandler(KeyEvent.KEY_PRESSED, new CanvasKeyEventHandler(this));

        root.getChildren().add(canvas);
        root.getChildren().add(getNewUiInstance());

        cameraTranslation = new Translate(0,0);

        startRenderTimer();
    }

    public BorderPane getNewUiInstance() {
        BorderPane pane = new BorderPane();
        pane.setBottom(getNewBottomPane());
        pane.setPickOnBounds(false); // allows the canvas to receive mouse events

        return pane;
    }

    private VBox getNewBottomPane() {
        VBox labelVBox = new VBox();
        labelVBox.setStyle("-fx-background-color: gainsboro;");

        popLabel = new Label("Population: " + "No current Settlements in this Kingdom me Lord");
        resourceLabel = new Label("Resource: " + "Me Lord! We have no resources!");
        typeLabel = new Label("Settlement Type: " + "Your people wander and suffer aimlessly");
        yearLabel = new Label("Current Year: " + initialYear);
        globalPopLabel = new Label("Global Population: " + "There are no homes for our women to give birth");
        settlementName = new Label("Settlement Name: ");

        labelVBox.getChildren().addAll(globalPopLabel, settlementName, typeLabel, popLabel, resourceLabel, yearLabel);

        return labelVBox;
    }

    private void startRenderTimer() {
        final Timer renderTimer = new Timer("RenderTimer", true);
        renderTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> render(canvas.getGraphicsContext2D()));
            }
        }, 0, MS_PER_SEC / FPS);
    }

    public void render(GraphicsContext gc) {
        secsSinceStart = (System.currentTimeMillis() - startTime) / MS_PER_SEC;
        currentYear = initialYear + (secsSinceStart / SECS_PER_YEAR);
        yearsFromStart = currentYear - initialYear;

        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        globalPopulation = 0d;
        for (Settlement settlement : settlements) {
            settlement.render(gc, cameraTranslation);
            globalPopulation += settlement.getPopulation();
        }

        updateUI();
    }

    public void updateUI() {
        globalPopLabel.setText("Global Population: " + Math.floor(globalPopulation));
        yearLabel.setText("Current Year: " + Math.floor(currentYear));

        if (target != null) {
            popLabel.setText("Population: " + Math.floor(target.getPopulation()));
            typeLabel.setText("Settlement Type: " + target.getType());
            resourceLabel.setText("Resources: " + target.getResources());
            settlementName.setText("Settlement Name: " + target.getName());
        }
    }

    public double getYearsFromStart() {
        return yearsFromStart;
    }

    public double getCurrentYear() {
        return currentYear;
    }

    public ArrayList<Settlement> getSettlements() {
        return settlements;
    }

    public void addSettlement(Settlement settlement) {
        settlements.add(settlement);
    }

    public Settlement getTarget() {
        return target;
    }

    public void setTarget(Settlement target) {
        this.target = target;
    }

    public void createNewSettlement(String name, double x, double y){
        Settlement settlement = new Settlement(this, 9000,x , y, name);
        settlements.add(settlement);
        setTarget(settlement);
    }
    public void promptName(double x, double y){
        TextInputDialog nameDialog = new TextInputDialog("Settlement " + settlements.size());
        nameDialog.setTitle("Set Settlement Name");
        nameDialog.setHeaderText("Name your newest City!!");
        nameDialog.setContentText("Please enter your the name for this settlement:");
        Optional<String> result = nameDialog.showAndWait();
        if (result.isPresent()) {
            if(!result.get().isEmpty())
                createNewSettlement(result.get(), x, y);
        }
    }

    public Translate getCameraTransform() {
        return cameraTranslation;
    }
}
