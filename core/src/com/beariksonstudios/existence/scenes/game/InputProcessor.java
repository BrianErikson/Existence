package com.beariksonstudios.existence.scenes.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.beariksonstudios.existence.gameobjects.settlement.Settlement;

/**
 * Created by BrianErikson on 8/9/2015.
 */
public class InputProcessor implements com.badlogic.gdx.InputProcessor {
    private final Game game;

    public InputProcessor(Game game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Clicked!");
        Camera camera = game.getStage().getCamera();
        Vector3 worldVec = camera.unproject(new Vector3(screenX, screenY, 0f));
        boolean hit = false;
        for (Settlement settlement : game.getSettlements()) {
            hit = settlement.getBoundingRectangle().contains(worldVec.x, worldVec.y);
            if (hit) {
                game.setTarget(settlement);
                break;
            }
        }

        if (!hit) {
            game.promptName(worldVec.x, worldVec.y);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
