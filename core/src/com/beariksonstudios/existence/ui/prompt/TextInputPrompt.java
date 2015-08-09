package com.beariksonstudios.existence.ui.prompt;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Created by BrianErikson on 8/9/2015.
 */
public class TextInputPrompt extends ChoicePrompt {
    private VisTextField field;

    public TextInputPrompt(Stage stage, String title, String text, String confirmText, String denyText) {
        super(stage, title, text, confirmText, denyText);

        VisWindow window = getWindow();
        int labelIndex = 0;
        for (int i = 0; i < window.getChildren().size; i++) {
            Actor actor = window.getChildren().get(i);
            if (actor instanceof VisLabel) {
                labelIndex = i;
                break;
            }
        }

        field = new VisTextField("");
        window.getChildren().insert(labelIndex, field);
    }

    public VisTextField getField() {
        return field;
    }
}