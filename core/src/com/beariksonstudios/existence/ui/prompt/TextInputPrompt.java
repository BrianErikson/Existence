package com.beariksonstudios.existence.ui.prompt;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.*;

/**
 * Created by BrianErikson on 8/9/2015.
 */
public class TextInputPrompt extends ChoicePrompt {
    private final VisTextButton confirm;
    private final VisTextButton deny;
    private VisTextField field;

    public TextInputPrompt(Stage stage, String title, String text, String confirmText, String denyText) {
        super(stage, title);

        final VisWindow window = getWindow();
        window.align(Align.topLeft);
        window.add(text).colspan(2).pad(5f).expand(true, false).row();

        field = new VisTextField("");
        window.add(field).colspan(2).pad(10f).expand().row();

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

    public VisTextField getField() {
        return field;
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