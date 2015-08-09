package com.beariksonstudios.existence.ui.prompt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.widget.VisWindow;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by BrianErikson on 8/9/2015.
 */
public abstract class Prompt {
    private boolean isOpen = true;
    private final VisWindow window;
    private final Stage stage;

    public Prompt(Stage stage, String title) {
        this.stage = stage;
        window = new VisWindow(title, true);
        window.setWidth(Gdx.graphics.getWidth() / 3f);
        window.setHeight(Gdx.graphics.getHeight() / 3f);
        window.setKeepWithinStage(true);

        window.addCloseButton();
        window.fadeOut(0);
        stage.addActor(window);
        window.centerWindow();
        window.fadeIn();
    }

    public VisWindow getWindow() {
        return window;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        if (!isOpen) {
            stage.addActor(window);
            window.centerWindow();
            window.fadeIn();
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
