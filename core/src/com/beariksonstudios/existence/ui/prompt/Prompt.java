package com.beariksonstudios.existence.ui.prompt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.widget.VisWindow;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by BrianErikson on 8/9/2015.
 */
public abstract class Prompt {
    private boolean isOpen = false;
    private final VisWindow window;
    private final Stage stage;

    public Prompt(Stage stage, String title) {
        this.stage = stage;
        window = new VisWindow(title);
        window.setWidth(Gdx.graphics.getWidth() / 3f);
        window.setHeight(Gdx.graphics.getHeight() / 3f);
        window.setKeepWithinStage(true);

        window.addCloseButton();

        // stifle new windows if clicking on window but not widgets
        window.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                return true;
            }
        });

        open();
    }

    public VisWindow getWindow() {
        return window;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        if (!isOpen) {
            isOpen = true;
            stage.addActor(window);
            window.centerWindow();
            //window.fadeIn();
        }
    }

    public void close() {
        isOpen = false;
        window.fadeOut();

        Timer timer = new Timer("PromptWindow", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                window.remove();
            }
        }, (long) VisWindow.FADE_TIME);
    }
}
