package com.beariksonstudios.existence.ui.prompt;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by BrianErikson on 8/9/2015.
 */
public abstract class ChoicePrompt extends Prompt {

    public ChoicePrompt(Stage stage, String title) {
        super(stage, title);
    }

    /** Interface Methods **/
    public void addConfirmListener(ChangeListener listener) {}
    public void addDenyListener(ChangeListener listener) {}
}
