package com.beariksonstudios.existence.scenes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.beariksonstudios.existence.gameobjects.ClickableObject;
import com.beariksonstudios.existence.gameobjects.settlement.Settlement;
import com.beariksonstudios.existence.resources.map.*;
import com.beariksonstudios.existence.ui.prompt.Prompt;
import com.beariksonstudios.existence.ui.prompt.SelectBoxPrompt;
import com.beariksonstudios.existence.ui.prompt.TextInputPrompt;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Neal on 7/3/2015.
 */
public class Game implements Screen {
    public static double MS_PER_SEC = 1000;
    public static double SECS_PER_YEAR = 10; // seconds (in real-time) per game-year
    public static float MAP_SIZE = 3000f;
    public static int SETTLEMENT_COST = 1000;
    private final InputProcessor input;

    private Stage stage;
    private Stage uiStage;

    private double startTime = System.currentTimeMillis();
    private double secsSinceStart;

    private double currentYear;
    private double initialYear;
    private double yearsFromStart;

    private VisLabel globalPopLabel;
    private VisLabel yearLabel;

    private float globalPopulation;
    private ArrayList<Settlement> settlements = new ArrayList<Settlement>();
    private ClickableObject target;

    private final Random RANDOM = new Random(System.currentTimeMillis());
    private ArrayList<MapResource> mapResources = new ArrayList<MapResource>();
    private Prompt openPrompt;
    private VisTable labelTable;

    public Game() {
        secsSinceStart = (System.currentTimeMillis() - startTime) / MS_PER_SEC;
        initialYear = 1900d;
        currentYear = initialYear + (secsSinceStart / SECS_PER_YEAR);
        globalPopulation = 0f;

        stage = new Stage();
        uiStage = new Stage();

        input = new InputProcessor(this);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, input, uiStage));

        Assets.load();
        
        generateResources();

        uiStage.addActor(getNewBottomPane());
    }

    public void generateResources() {
        for(int i = 0; i < 6; i++) {
            float x = RANDOM.nextFloat()  * MAP_SIZE;
            float y = RANDOM.nextFloat()  * MAP_SIZE;
            addMapResource(new FertileLand(1000, x, y));
            addMapResource(new Mountain(1000, RANDOM.nextFloat() * MAP_SIZE, RANDOM.nextFloat() * MAP_SIZE));
            addMapResource(new Forest(1000,RANDOM.nextFloat() * MAP_SIZE, RANDOM.nextFloat() * MAP_SIZE));
            addMapResource(new Lake(1000,RANDOM.nextFloat() * MAP_SIZE, RANDOM.nextFloat() * MAP_SIZE));
        }
    }
    
    private void addMapResource(final MapResource mapResource) {
        final Game _this = this;
        mapResource.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                _this.setTarget(mapResource);
                return true;
            }
        });
        mapResources.add(mapResource);
        stage.addActor(mapResource);
    }

    private VisTable getNewBottomPane() {

        labelTable = new VisTable(true);
        labelTable.align(Align.topLeft);
        NinePatchDrawable tableBg = new NinePatchDrawable(new NinePatch(Assets.manager.get("ui/bottomPane.png",
                Texture.class), 8,8,8,8));
        labelTable.setBackground(tableBg);
        labelTable.setWidth(Gdx.graphics.getWidth());
        labelTable.setHeight(Gdx.graphics.getHeight() / 3.5f);

        yearLabel = new VisLabel("Current Year: " + initialYear);
        globalPopLabel = new VisLabel("Global Population: " + "There are no homes for our women to give birth");

        labelTable.add(yearLabel).fill(true, false);
        labelTable.row();
        labelTable.add(globalPopLabel).fill(true, false);

        return labelTable;
    }

    public void updateUI() {
        globalPopLabel.setText("Global Population: " + Math.floor(globalPopulation));
        yearLabel.setText("Current Year: " + Math.floor(currentYear));

        if (openPrompt != null && !openPrompt.isOpen()) {
            openPrompt = null;
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

    public ClickableObject getTarget() {
        return target;
    }

    public void setTarget(ClickableObject target) {
        if(this.target != null) {
            this.target.untarget();
        }
        this.target = target;
        this.target.setAsTarget();
        labelTable.clear();

        labelTable.add(yearLabel).fill(true, false);
        labelTable.row();
        labelTable.add(globalPopLabel).fill(true, false);
        labelTable.row();

        for (VisLabel label : target.getLabels()) {
            labelTable.add(label).fill(true, false);
            labelTable.row();
        }
    }

    public Settlement createNewSettlement(String name, float x, float y){
        final Settlement settlement = new Settlement(this, SETTLEMENT_COST, x, y, name);
        final Game _this = this;
        settlement.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                _this.setTarget(settlement);
                return true;
            }
        });

        settlements.add(settlement);
        stage.addActor(settlement);
        setTarget(settlement);
        return settlement;
    }

    public Stage getStage() {
        return stage;
    }

    public Stage getUiStage() {
        return uiStage;
    }

    public void closeOpenPrompt() {
        if (openPrompt != null && openPrompt.isOpen()) {
            openPrompt.close();
        }
        openPrompt = null;
    }

    public void promptName(final float x, final float y){
        if (openPrompt != null) {
            return;
        }

        final TextInputPrompt prompt = new TextInputPrompt(getUiStage(), "Set Settlement Name", "Name your newest " +
                "city!!", "OK",
                "Cancel");
        openPrompt = prompt;

        prompt.addConfirmListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String text = prompt.getField().getText();
                if (text.length() > 0) {
                    if (settlements.size() < 1) {
                        closeOpenPrompt();
                        createNewSettlement(text, x, y);
                    }
                    else {
                        closeOpenPrompt();
                        promptPopulationChoice(text, x, y);
                    }
                }
            }
        });
    }
    public void promptPopulationChoice(final String name, final float x, final float y){
        if (openPrompt != null) {
            return;
        }

        Array<String> names = new Array<String>();
        for(int i = 0; i < settlements.size(); i++){
            names.add(settlements.get(i).getName());
        }

        final SelectBoxPrompt prompt = new SelectBoxPrompt(getUiStage(), "Choose a City", "Which city would you like " +
                "to steal" +
                " 1000 population from?" ,names, "OK", "Cancel");
        openPrompt = prompt;

        prompt.addConfirmListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String takeFrom = prompt.getSelectedItem();
                boolean removed = false;
                for (Settlement settlement : settlements) {
                    if(settlement.getName().equals(takeFrom)){
                        settlement.addPopulation(-SETTLEMENT_COST);
                        removed = true;
                        break;
                    }
                }

                if (removed) {
                    closeOpenPrompt();
                    createNewSettlement(name, x, y);
                }
            }
        });
    }

    public ArrayList<MapResource> getMapResources() {
        return mapResources;
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        secsSinceStart = (System.currentTimeMillis() - startTime) / MS_PER_SEC;
        currentYear = initialYear + (secsSinceStart / SECS_PER_YEAR);
        yearsFromStart = currentYear - initialYear;

        Gdx.gl.glClearColor(0f, 0.7f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if (openPrompt == null) {
            input.update(); // game input (WSAD, etc)
        }

        stage.act();
        stage.draw();

        uiStage.act();
        uiStage.draw();

        globalPopulation = 0;
        for (Settlement settlement : settlements) {
            globalPopulation += settlement.getPopulation();
        }
        
        updateUI();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
