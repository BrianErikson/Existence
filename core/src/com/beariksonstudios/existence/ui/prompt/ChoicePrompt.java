package com.beariksonstudios.existence.ui.prompt;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Created by BrianErikson on 8/9/2015.
 */
public class ChoicePrompt extends Prompt {
    private final VisTextButton confirm;
    private final VisTextButton deny;

    public ChoicePrompt(Stage stage, String title, String text, String confirmText, String denyText) {
        super(stage, title);

        final VisWindow window = getWindow();
        window.add(text).row();

        VisTable table = new VisTable(true);

        confirm = new VisTextButton(confirmText);
        deny = new VisTextButton(denyText);

        table.add(confirm).align(Align.left);
        table.add(deny).align(Align.right);

        addDenyListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                close();
            }
        });
    }

    public void addConfirmListener(ChangeListener listener) {
        confirm.addListener(listener);
    }

    public void addDenyListener(ChangeListener listener) {
        deny.addListener(listener);
    }
}
