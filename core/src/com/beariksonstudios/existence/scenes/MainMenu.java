package com.beariksonstudios.existence.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.beariksonstudios.existence.Existence;
import com.beariksonstudios.existence.scenes.game.Game;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

/**
 * Created by Neal on 7/3/2015.
 */
public class MainMenu implements Screen {
    private Stage stage;

    public MainMenu() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        VisTable table = new VisTable(true);
        Container<VisTable> center = new Container<VisTable>(table);

        VisTextButton newGame = new VisTextButton("New Game");
        VisTextButton loadGame = new VisTextButton("Load Game");
        VisTextButton options = new VisTextButton("Options");
        VisTextButton exit = new VisTextButton("Exit");

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Existence.fetch().setScreen(new Game());
            }
        });

        table.add(newGame).fill(true, false);
        table.row();
        table.add(loadGame).fill(true, false);
        table.row();
        table.add(options).fill(true, false);
        table.row();
        table.add(exit).fill(true, false);

        center.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);

        stage.addActor(center);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
