package com.olaz.coreforge.world.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Tile {
    private TileType type;
    private Texture texture;
    private TileBorder borderTextureRegion;
    private int borderMask;
    private Vector2 position;
    private boolean hasBlock = false;

    public Tile(TileType type, Texture texture, Vector2 position) {
        this.type = type;
        this.texture = texture;
        this.position = position;
    }

    public Tile(TileType type, Texture texture, Vector2 position, TileBorder borderTextures) {
        this(type, texture, position);
        setBorderTextureRegion(borderTextures);
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public boolean isHasBlock() {
        return hasBlock;
    }

    public void setHasBlock(boolean hasBlock) {
        this.hasBlock = hasBlock;
    }

    public TileBorder getBorderTextureRegion() {
        return borderTextureRegion;
    }

    public void setBorderTextureRegion(TileBorder borderTextureRegion) {
        this.borderTextureRegion = borderTextureRegion;
    }

    public int getBorderMask() {
        return borderMask;
    }

    public void setBorderMask(int borderMask) {
        this.borderMask = borderMask;
    }

    @Override
    public String toString() {
        return "Tile{" +
            "type=" + type +
            ", position=" + position +
            '}';
    }
}
