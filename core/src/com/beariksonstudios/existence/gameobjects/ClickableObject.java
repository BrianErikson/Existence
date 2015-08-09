package com.beariksonstudios.existence.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.ui.widget.VisLabel;

import java.util.ArrayList;

/**
 * Created by Neal on 7/31/2015.
 */
public interface ClickableObject {
    public ArrayList<VisLabel> getLabels();

    public String getName();

    public void setAsTarget();

    public void untarget();

    public Vector2 getPosition();
}
