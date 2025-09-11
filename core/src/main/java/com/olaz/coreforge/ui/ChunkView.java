package com.olaz.coreforge.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.olaz.coreforge.world.Chunk;
import com.olaz.coreforge.world.tiles.Tile;

public class ChunkView extends Actor {
    private final Chunk chunk;
    private float zoom = 1.0f;
    private final float minZoom = 1.0f;
    private final float maxZoom = 5.0f;
    private Vector2 offset = new Vector2(0, 0);

    public ChunkView(Chunk chunk, float width, float height) {
        this.chunk = chunk;
        this.setSize(width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float tileSize = (getWidth() / chunk.getSize().getWidth()) * zoom;

        for (Tile tile : chunk.getTiles()) {
            float x = getX() + offset.x + tile.getPosition().x * tileSize;
            float y = getY() + offset.y + tile.getPosition().y * tileSize;
            if (tile.getTexture() != null) {
                batch.draw(tile.getTexture(), x, y, tileSize, tileSize);
            }
        }
    }

    @Override
    public void act(float delta) {
       pollingUpdate(delta);
    }

    public void pollingUpdate(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.PLUS) || Gdx.input.isKeyPressed(Input.Keys.EQUALS))
            this.zoomIn(delta * 2);
        if (Gdx.input.isKeyPressed(Input.Keys.MINUS))
            this.zoomOut(delta * 2);
        if (Gdx.input.isKeyPressed(Input.Keys.R))
            this.resetZoom();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            this.addOffset(new Vector2(1, 0));
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            this.addOffset(new Vector2(-1, 0));
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            this.addOffset(new Vector2(0, -1));
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            this.addOffset(new Vector2(0, 1));
    }
    public float getZoom() {
        return this.zoom;
    }

    public void setZoom(float zoom) {
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;

        float worldX = (centerX - offset.x) / this.zoom;
        float worldY = (centerY - offset.y) / this.zoom;

        this.zoom = MathUtils.clamp(zoom, minZoom, maxZoom);

        offset.x = centerX - worldX * this.zoom;
        offset.y = centerY - worldY * this.zoom;

        clampOffsetToEdges();
    }

    public void zoomIn(float delta) {
        setZoom(zoom + delta);
    }

    public void zoomOut(float delta) {
        setZoom(zoom - delta);
    }

    public void resetZoom() {
        zoom = 1.0f;
        resetOffset();
    }

    public void setOffset(Vector2 offset) {
        if (zoom <= 1f) return;;
        this.offset = offset;
        clampOffsetToEdges();
    }

    public void addOffset(Vector2 delta) {
        if (zoom <= 1f) return;
        offset.add(delta);
        clampOffsetToEdges();
    }

    public Vector2 getOffset() {
        return this.offset;
    }

    public void resetOffset() {
        this.offset.set(0, 0);
    }

    public void clampOffsetToEdges() {
        float tileSize = (getWidth() / chunk.getSize().getWidth()) * zoom;
        float contentWidth = chunk.getSize().getWidth() * tileSize;
        float contentHeight = chunk.getSize().getHeight() * tileSize;

        float minX = Math.min(0, getWidth() - contentWidth);
        float maxX = 0;
        float minY = Math.min(0, getHeight() - contentHeight);
        float maxY = 0;

        offset.x = MathUtils.clamp(offset.x, minX, maxX);
        offset.y = MathUtils.clamp(offset.y, minY, maxY);
    }
}
