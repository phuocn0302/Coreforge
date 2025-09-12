package com.olaz.coreforge.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.olaz.coreforge.world.Chunk;
import com.olaz.coreforge.world.tiles.Tile;
import com.olaz.coreforge.world.tiles.TileBorder;

public class ChunkView extends Actor {
    private final Chunk chunk;
    private float zoom = 1.0f;
    private final float minZoom = 1.0f;
    private final float maxZoom = 5.0f;
    private Vector2 offset = new Vector2(0, 0);

    public ChunkView(Chunk chunk, float width, float height) {
        this.chunk = chunk;
        this.setSize(width, height);

        for (Tile tile : chunk.getTiles()) {
            updateTileBorders(tile);
        }
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

            drawTileBorder(batch, tile, x, y, tileSize);
        }
    }

    private void drawTileBorder(Batch batch, Tile tile, float x, float y, float tileSize) {
        TileBorder borders = tile.getBorderTextureRegion();
        if (borders == null) return;

        int mask = tile.getBorderMask();

        if ((mask & TileBorder.BorderMask.TOP) != 0)
            batch.draw(borders.top, x, y, tileSize, tileSize);
        if ((mask & TileBorder.BorderMask.BOTTOM) != 0)
            batch.draw(borders.bottom, x, y, tileSize, tileSize);
        if ((mask & TileBorder.BorderMask.LEFT) != 0)
            batch.draw(borders.left, x, y, tileSize, tileSize);
        if ((mask & TileBorder.BorderMask.RIGHT) != 0)
            batch.draw(borders.right, x, y, tileSize, tileSize);

        if ((mask & TileBorder.BorderMask.TOP_LEFT) != 0)
            batch.draw(borders.topLeft, x, y, tileSize, tileSize);
        if ((mask & TileBorder.BorderMask.TOP_RIGHT) != 0)
            batch.draw(borders.topRight, x, y, tileSize, tileSize);
        if ((mask & TileBorder.BorderMask.BOTTOM_LEFT) != 0)
            batch.draw(borders.bottomLeft, x, y, tileSize, tileSize);
        if ((mask & TileBorder.BorderMask.BOTTOM_RIGHT) != 0)
            batch.draw(borders.bottomRight, x, y, tileSize, tileSize);

        if ((mask & TileBorder.BorderMask.INNER_TOP_LEFT) != 0)
            batch.draw(borders.innerTopLeft, x, y, tileSize, tileSize);
        if ((mask & TileBorder.BorderMask.INNER_TOP_RIGHT) != 0)
            batch.draw(borders.innerTopRight, x, y, tileSize, tileSize);
        if ((mask & TileBorder.BorderMask.INNER_BOTTOM_LEFT) != 0)
            batch.draw(borders.innerBottomLeft, x, y, tileSize, tileSize);
        if ((mask & TileBorder.BorderMask.INNER_BOTTOM_RIGHT) != 0)
            batch.draw(borders.innerBottomRight, x, y, tileSize, tileSize);
    }

    private void updateTileBorders(Tile tile) {
        int mask = 0;

        // Edges
        if (isTileDifferent(tile, 0, 1)) mask |= TileBorder.BorderMask.TOP;
        if (isTileDifferent(tile, 0, -1)) mask |= TileBorder.BorderMask.BOTTOM;
        if (isTileDifferent(tile, -1, 0)) mask |= TileBorder.BorderMask.LEFT;
        if (isTileDifferent(tile, 1, 0)) mask |= TileBorder.BorderMask.RIGHT;

        // Corners
        if (isTileDifferent(tile, -1, 1) && isTileDifferent(tile, 0, 1) && isTileDifferent(tile, -1, 0))
            mask |= TileBorder.BorderMask.TOP_LEFT;
        if (isTileDifferent(tile, 1, 1) && isTileDifferent(tile, 0, 1) && isTileDifferent(tile, 1, 0))
            mask |= TileBorder.BorderMask.TOP_RIGHT;
        if (isTileDifferent(tile, -1, -1) && isTileDifferent(tile, 0, -1) && isTileDifferent(tile, -1, 0))
            mask |= TileBorder.BorderMask.BOTTOM_LEFT;
        if (isTileDifferent(tile, 1, -1) && isTileDifferent(tile, 0, -1) && isTileDifferent(tile, 1, 0))
            mask |= TileBorder.BorderMask.BOTTOM_RIGHT;

        // Inner corners
        if (!isTileDifferent(tile, -1, 0) && !isTileDifferent(tile, 0, 1) && isTileDifferent(tile, -1, 1))
            mask |= TileBorder.BorderMask.INNER_TOP_LEFT;
        if (!isTileDifferent(tile, 1, 0) && !isTileDifferent(tile, 0, 1) && isTileDifferent(tile, 1, 1))
            mask |= TileBorder.BorderMask.INNER_TOP_RIGHT;
        if (!isTileDifferent(tile, -1, 0) && !isTileDifferent(tile, 0, -1) && isTileDifferent(tile, -1, -1))
            mask |= TileBorder.BorderMask.INNER_BOTTOM_LEFT;
        if (!isTileDifferent(tile, 1, 0) && !isTileDifferent(tile, 0, -1) && isTileDifferent(tile, 1, -1))
            mask |= TileBorder.BorderMask.INNER_BOTTOM_RIGHT;

        tile.setBorderMask(mask);
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
        if (zoom <= 1f) return;
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

    private boolean isTileDifferent(Tile tile, int offsetX, int offsetY) {
        int nx = (int) (tile.getPosition().x + offsetX);
        int ny = (int) (tile.getPosition().y + offsetY);

        Tile neighbor = chunk.getTile(nx, ny);

        if (neighbor == null) {
            return true; // no tile = different
        }

        return !neighbor.getType().equals(tile.getType());
    }
}
