package com.beariksonstudios.existence.scenes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Array;
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
    public static long MS_PER_SEC = 1000;
    public static float SECS_PER_YEAR = 10; // seconds (in real-time) per game-year
    public static float MAP_SIZE = 3000;
    public static int SETTLEMENT_COST = 10000;
    private final InputProcessor input;

    private Stage stage;
    private Stage uiStage;

    private VisLabel popLabel;
    private VisLabel resourceLabel;
    private VisLabel yearLabel;
    private VisLabel typeLabel;
    private VisLabel globalPopLabel;
    private VisLabel settlementName;

    private float startTime = System.currentTimeMillis();
    private float secsSinceStart;

    private float currentYear;
    private float initialYear;
    private float yearsFromStart;

    private float globalPopulation;
    private ArrayList<Settlement> settlements = new ArrayList<Settlement>();
    private Settlement target;

    public static int MOVE_SPEED = 10;
    private final Random RANDOM = new Random(System.currentTimeMillis());
    private ArrayList<MapResource> mapResources = new ArrayList<MapResource>();
    private Prompt openPrompt;

    public Game() {
        secsSinceStart = (System.currentTimeMillis() - startTime) / MS_PER_SEC;
        initialYear = 1900;
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
    
    private void addMapResource(MapResource mapResource) {
        mapResources.add(mapResource);
        stage.addActor(mapResource);
    }

    private VisTable getNewBottomPane() {

        VisTable table = new VisTable(true);
        NinePatchDrawable tableBg = new NinePatchDrawable(new NinePatch(Assets.manager.get("ui/bottomPane.png",
                Texture.class), 8,8,8,8));
        table.setBackground(tableBg);
        table.setWidth(Gdx.graphics.getWidth());
        table.setHeight(Gdx.graphics.getHeight() / 4f);

        popLabel = new VisLabel("Population: " + "No current Settlements in this Kingdom me Lord");
        resourceLabel = new VisLabel("Resource: " + "Me Lord! We have no resources!");
        typeLabel = new VisLabel("Settlement Type: " + "Your people wander and suffer aimlessly");
        yearLabel = new VisLabel("Current Year: " + initialYear);
        globalPopLabel = new VisLabel("Global Population: " + "There are no homes for our women to give birth");
        settlementName = new VisLabel("Settlement Name: ");

        table.add(popLabel).fill(true, false).expand();
        table.row();
        table.add(resourceLabel).fill(true, false);
        table.row();
        table.add(typeLabel).fill(true, false);
        table.row();
        table.add(yearLabel).fill(true, false);
        table.row();
        table.add(globalPopLabel).fill(true, false);
        table.row();
        table.add(settlementName).fill(true, false);

        return table;
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

        if (openPrompt != null && !openPrompt.isOpen()) {
            openPrompt = null;
        }
    }

    public float getYearsFromStart() {
        return yearsFromStart;
    }

    public float getCurrentYear() {
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

    public Settlement createNewSettlement(String name, float x, float y){
        Settlement settlement = new Settlement(this, 9000,x , y, name);
        settlements.add(settlement);
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

        input.update();

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