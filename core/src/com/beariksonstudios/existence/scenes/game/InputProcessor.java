package com.beariksonstudios.existence.scenes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.beariksonstudios.existence.gameobjects.settlement.Settlement;

/**
 * Created by BrianErikson on 8/9/2015.
 */
public class InputProcessor implements com.badlogic.gdx.InputProcessor {
    public static float MOVE_SPEED = 500f;
    private final Game game;
    private final Camera camera;

    public InputProcessor(Game game) {
        this.game = game;
        camera = game.getStage().getCamera();
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.position.add(0f, MOVE_SPEED * Gdx.graphics.getDeltaTime(), 0f);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.position.add(0f, -MOVE_SPEED * Gdx.graphics.getDeltaTime(), 0f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.position.add(MOVE_SPEED * Gdx.graphics.getDeltaTime(), 0f, 0f);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.position.add(-MOVE_SPEED * Gdx.graphics.getDeltaTime(), 0f, 0f);
        }
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
        Camera camera = game.getStage().getCamera();
        Vector3 worldVec = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f));
        boolean hit = false;
        for (Settlement settlement : game.getSettlements()) {
            Rectangle rect = new Rectangle(settlement.getX(), settlement.getY(), settlement.getImageWidth(),
                    settlement.getImageHeight());
            hit = rect.contains(worldVec.x, worldVec.y);
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
