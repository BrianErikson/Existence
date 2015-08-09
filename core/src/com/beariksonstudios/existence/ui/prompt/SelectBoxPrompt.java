package com.beariksonstudios.existence.ui.prompt;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.*;

/**
 * Created by BrianErikson on 8/9/2015.
 */
public class SelectBoxPrompt extends ChoicePrompt {
    private final VisTextButton confirm;
    private final VisTextButton deny;
    private final VisSelectBox<String> selector;

    public SelectBoxPrompt(Stage stage, String title, String text, Array<String> choices, String confirmText,
                           String denyText) {
        super(stage, title);

        final VisWindow window = getWindow();
        window.align(Align.topLeft);
        window.add(text).colspan(2).pad(5f).expand(true, false).row();

        selector = new VisSelectBox<String>();
        selector.setItems(choices);
        window.add(selector).colspan(2).pad(10f).expand().row();

        confirm = new VisTextButton(confirmText);
        deny = new VisTextButton(denyText);

        window.add(confirm).pad(5f).align(Align.left);
        window.add(deny).pad(5f).align(Align.right);
        window.pack();

        addDenyListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                close();
            }
        });
    }

    public VisSelectBox<String> getSelector() {
        return selector;
    }

    public String getSelectedItem() {
        return selector.getSelected();
    }

    @Override
    public void addConfirmListener(ChangeListener listener) {
        confirm.addListener(listener);
    }

    @Override
    public void addDenyListener(ChangeListener listener) {
        deny.addListener(listener);
    }
}
