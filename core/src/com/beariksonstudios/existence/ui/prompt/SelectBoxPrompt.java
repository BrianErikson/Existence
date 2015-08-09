package com.beariksonstudios.existence.ui.prompt;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Created by BrianErikson on 8/9/2015.
 */
public class SelectBoxPrompt extends ChoicePrompt {
    private final VisSelectBox<String> selector;

    public SelectBoxPrompt(Stage stage, String title, String text, Array<String> choices, String confirmText,
                           String denyText) {
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

        selector = new VisSelectBox<String>();
        selector.setItems(choices);
        window.getChildren().insert(labelIndex, selector);
    }

    public VisSelectBox<String> getSelector() {
        return selector;
    }

    public String getSelectedItem() {
        return selector.getSelected();
    }
}
