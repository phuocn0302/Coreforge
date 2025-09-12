package com.olaz.coreforge.ui;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.olaz.coreforge.world.tiles.Tile;

public class ChunkViewInputHandler extends InputAdapter implements GestureDetector.GestureListener {
    private final ChunkView view;
    private float initialZoom;

    public ChunkViewInputHandler(ChunkView view) {
        this.view = view;
    }

    public void pollingUpdate(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.PLUS) || Gdx.input.isKeyPressed(Input.Keys.EQUALS))
            view.zoomIn(delta * 2);
        if (Gdx.input.isKeyPressed(Input.Keys.MINUS))
            view.zoomOut(delta * 2);
        if (Gdx.input.isKeyPressed(Input.Keys.R))
            view.resetZoom();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            view.addOffset(new Vector2(1, 0));
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            view.addOffset(new Vector2(-1, 0));
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            view.addOffset(new Vector2(0, -1));
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            view.addOffset(new Vector2(0, 1));
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        float screenX = Gdx.input.getX();
        float screenY = Gdx.input.getY();

        if (!isInsideView(screenX, screenY)) return false;

        if (amountY > 0) view.zoomOut(0.5f);
        if (amountY < 0) view.zoomIn(0.5f);
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (!isInsideView(x, y)) return false;

        view.addOffset(new Vector2(deltaX * 0.3f, -deltaY * 0.3f));
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        if (!isInsideView(pointer1.x , pointer2.y)) return false;

        float initialDistance = initialPointer1.dst(initialPointer2);
        float currentDistance = pointer1.dst(pointer2);
        view.setZoom(initialZoom * (currentDistance / initialDistance));
        return true;
    }

    @Override
    public void pinchStop() {
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!isInsideView(screenX, screenY)) return false;

        initialZoom = view.getZoom();
        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (!isInsideView(x, y)) return false;

        Tile tile = view.getTileScreen(x,y);

        if (tile == null) {
            return false;
        }

        if (tile.isCanInteract()) {
            tile.onInteract();
        }

        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    private boolean isInsideView(float screenX, float screenY) {
        Vector2 stageCoords = view.getStage().screenToStageCoordinates(new Vector2(screenX, screenY));
        Vector2 localCoords = view.stageToLocalCoordinates(stageCoords);
        return localCoords.x >= 0 && localCoords.x <= view.getWidth()
            && localCoords.y >= 0 && localCoords.y <= view.getHeight();
    }
}
