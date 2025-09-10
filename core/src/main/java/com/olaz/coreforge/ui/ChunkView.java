package com.olaz.coreforge.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.olaz.coreforge.world.Chunk;
import com.olaz.coreforge.world.tiles.Tile;

public class ChunkView extends Actor {
    private final Chunk chunk;

    public ChunkView(Chunk chunk, float width, float height) {
        this.chunk = chunk;
        this.setSize(width, height);

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float tileSize = (getWidth() / chunk.getSize().getWidth());

        for (Tile tile : chunk.getTiles()) {
            float x = getX() + tile.getPosition().x * tileSize;
            float y = getY() + tile.getPosition().y * tileSize;
            if (tile.getTexture() != null) {
                batch.draw(tile.getTexture(), x, y, tileSize, tileSize);
            }
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
