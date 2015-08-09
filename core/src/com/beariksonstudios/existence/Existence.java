package com.beariksonstudios.existence;

import com.badlogic.gdx.Game;
import com.beariksonstudios.existence.scenes.MainMenu;
import com.kotcrab.vis.ui.VisUI;

/**
 * Created by Neal on 7/3/2015.
 */
public class Existence extends Game {
    private static Existence singleton;

    public Existence() {
        singleton = this;
    }

    @Override
    public void create() {
        VisUI.load();
        setScreen(new MainMenu());
    }

    public static Existence fetch() {
        if (singleton == null)
            singleton = new Existence();
        return singleton;
    }

    @Override
    public void dispose() {
        super.dispose();
        VisUI.dispose();
    }
}
