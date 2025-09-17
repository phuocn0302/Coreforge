package com.olaz.coreforge.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.olaz.coreforge.blocks.Block;
import com.olaz.coreforge.data.Size;
import com.olaz.coreforge.world.tiles.Tile;

public abstract class Chunk {
    private final Size size = new Size(32, 32);
    private Array<Tile> tiles;
    private Array<Block> blocks;

    public Chunk() {
        this.tiles = new Array<>(size.getArea());
        this.blocks = new Array<>(size.getArea());

        this.tiles.size = size.getArea();
        this.blocks.size = size.getArea();
        setupChunkLayout();
    }

    public abstract void setupChunkLayout();

    public Size getSize() {
        return size;
    }

    public Array<Tile> getTiles() {
        return tiles;
    }

    public Array<Block> getBlocks() {
        return blocks;
    }

    public Tile getTile(Vector2 position) {
        return getTile(position.x, position.y);
    }

    public Block getBlock(Vector2 position) {
        return getBlock(position.x, position.y);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= size.getWidth() || y < 0 || y >= size.getHeight()) {
            return null;
        }
        return tiles.get(y * size.getWidth() + x);
    }

    public Block getBlock(int x, int y) {
        if (x < 0 || x >= size.getWidth() || y < 0 || y >= size.getHeight()) {
            return null;
        }
        return blocks.get(y * size.getWidth() + x);
    }

    public Tile getTile(float x, float y) {
        return getTile((int) x, (int) y);
    }

    public Block getBlock(float x, float y) {
        return getBlock((int) x, (int) y);
    }

    public Tile getTile(int index) {
        if (index < 0 || index >= size.getWidth() * size.getHeight()) {
            return null;
        }
        return tiles.get(index);
    }

    public Block getBlock(int index) {
        if (index < 0 || index >= size.getWidth() * size.getHeight()) {
            return null;
        }
        return blocks.get(index);
    }

    public void setTiles(Array<Tile> tiles) {
        this.tiles = tiles;
    }

    public void placeBlock(Block block, Vector2 position) {
        if (position.x < 0 || position.x >= size.getWidth() || position.y < 0 || position.y >= size.getHeight()) {
            return;
        }

        this.blocks.set((int) (position.y * getSize().getWidth() + position.x), block);
    }

    public void placeBlock(Block block, float x, float y) {
        if (x < 0 || x >= size.getWidth() || y < 0 || y >= size.getHeight()) {
            return;
        }

        this.blocks.set((int) (y * getSize().getWidth() + x), block);
    }
}
