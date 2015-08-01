package com.beariksonstudios.existence.scenes.game;

import com.beariksonstudios.existence.Existence;
import com.beariksonstudios.existence.gameobjects.settlement.Settlement;
import com.beariksonstudios.existence.resources.map.*;
import com.beariksonstudios.existence.scenes.Camera;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

/**
 * Created by Neal on 7/3/2015.
 */
public class Game extends Scene {
    public static long MS_PER_SEC = 1000;
    public static long FPS = 30;
    public static double SECS_PER_YEAR = 10; // seconds (in real-time) per game-year
    public static double MAP_SIZE = 3000;
    public static int MOVE_SPEED = 10;
    private final Random RANDOM = new Random(System.currentTimeMillis());
    private Canvas canvas;
    private String userName;
    private Label globalPopLabel;
    private Label yearLabel;
    private double startTime = System.currentTimeMillis();
    private double secsSinceStart;
    private double currentYear;
    private double initialYear;
    private double yearsFromStart;
    private double globalPopulation;
    private ArrayList<Settlement> settlements = new ArrayList<Settlement>();
    private ClickableObject target;
    private Camera camera;
    private ArrayList<MapResource> mapResources = new ArrayList<MapResource>();
    private VBox labelVBox;

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

        camera = new Camera();
        camera.setPosition(new Point2D(MAP_SIZE / 2d, MAP_SIZE / 2d));

        for (int i = 0; i < 15; i++) {
            RANDOM.setSeed(System.nanoTime());
            mapResources.add(new FertileLand(1000, RANDOM.nextDouble() * -MAP_SIZE, RANDOM.nextDouble() * -MAP_SIZE));
            mapResources.add(new Mountain(1000, RANDOM.nextDouble() * -MAP_SIZE, RANDOM.nextDouble() * -MAP_SIZE));
            mapResources.add(new Forest(1000, RANDOM.nextDouble() * -MAP_SIZE, RANDOM.nextDouble() * -MAP_SIZE));
            mapResources.add(new Lake(1000, RANDOM.nextDouble() * -MAP_SIZE, RANDOM.nextDouble() * -MAP_SIZE));
        }

        for (int i = 0; i < 15; i++) {
            RANDOM.setSeed(System.nanoTime());
            mapResources.add(new FertileLand(1000, RANDOM.nextDouble() * -MAP_SIZE+1500, RANDOM.nextDouble() * -MAP_SIZE+1500));
            mapResources.add(new Mountain(1000, RANDOM.nextDouble() * -MAP_SIZE+1500, RANDOM.nextDouble() * -MAP_SIZE+1500));
            mapResources.add(new Forest(1000, RANDOM.nextDouble() * -MAP_SIZE+1500, RANDOM.nextDouble() * -MAP_SIZE+1500));
            mapResources.add(new Lake(1000, RANDOM.nextDouble() * -MAP_SIZE+1500, RANDOM.nextDouble() * -MAP_SIZE+1500));
        }

        startRenderTimer();
    }

    public BorderPane getNewUiInstance() {
        BorderPane pane = new BorderPane();
        pane.setBottom(getNewBottomPane());
        pane.setPickOnBounds(false); // allows the canvas to receive mouse events

        return pane;
    }

    private VBox getNewBottomPane() {
        labelVBox = new VBox();
        labelVBox.setStyle("-fx-background-color: gainsboro;");

        globalPopLabel = new Label("Global Population: " + "There are no homes for our women to give birth");
        yearLabel = new Label("Current Year: " + initialYear);

        labelVBox.getChildren().addAll(globalPopLabel, yearLabel);

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

        for (MapResource mapResource : mapResources) {
            mapResource.render(gc, camera);
        }

        globalPopulation = 0d;
        for (Settlement settlement : settlements) {
            settlement.render(gc, camera);
            globalPopulation += settlement.getPopulation();
        }

        camera.update();
        updateUI();
    }

    public void updateUI() {
        globalPopLabel.setText("Global Population: " + Math.floor(globalPopulation));
        yearLabel.setText("Current Year: " + Math.floor(currentYear));


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

    public ClickableObject getTarget() {
        return target;
    }

    public void setTarget(ClickableObject target) {
        if(this.target != null) {
            this.target.untarget();
        }
            this.target = target;
            this.target.setAsTarget();
            labelVBox.getChildren().clear();
            labelVBox.getChildren().addAll(globalPopLabel, yearLabel);
            labelVBox.getChildren().addAll(target.getLabels());
    }

    public Settlement createNewSettlement(String name, double x, double y) {
        Settlement settlement = new Settlement(this, 900000, x, y, name);
        settlements.add(settlement);
        setTarget(settlement);
        return settlement;
    }

    public void promptName(double x, double y) {
        TextInputDialog nameDialog = new TextInputDialog("Settlement " + settlements.size());
        nameDialog.setTitle("Set Settlement Name");
        nameDialog.setHeaderText("Name your newest City!!");
        nameDialog.setContentText("Please enter your the name for this settlement:");
        Optional<String> result = nameDialog.showAndWait();
        if (result.isPresent()) {
            if (!result.get().isEmpty())
                if (settlements.size() == 0) {
                    createNewSettlement(result.get(), x, y);
                } else {
                    promptPopulationChoice(result.get(), x, y);
                }
        }
    }

    public void promptPopulationChoice(String name, double x, double y) {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < settlements.size(); i++) {
            names.add(settlements.get(i).getName());
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(names.get(0), names);
        dialog.setTitle("Choose a City");
        dialog.setHeaderText("Which city would you like to steal 1000 population from?");
        dialog.setContentText("Choose the city here: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            createNewSettlement(name, x, y);
            for (Settlement settlement : settlements) {
                if (settlement.getName().equals(result.get())) {
                    settlement.addPopulation(-1000);
                    break;
                }
            }

        }
    }

    public Camera getCameraTransform() {
        return camera;
    }

    public ArrayList<MapResource> getMapResources() {
        return mapResources;
    }
}
