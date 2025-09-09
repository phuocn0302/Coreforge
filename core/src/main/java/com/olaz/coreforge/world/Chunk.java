package com.olaz.coreforge.world;

import com.badlogic.gdx.utils.Array;
import com.olaz.coreforge.data.Size;
import com.olaz.coreforge.world.tiles.Tile;

public abstract class Chunk {
    private final Size size = new Size(32,32);
    private Array<Tile> tiles;

    public Chunk() {
        this.tiles = new Array<>(size.getWidth() * size.getHeight());
        setupChunkLayout();
    }

    public abstract void setupChunkLayout();

    public Size getSize() {
        return size;
    }

    public Array<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(Array<Tile> tiles) {
        this.tiles = tiles;
    }
}
