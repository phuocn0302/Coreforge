package com.olaz.coreforge.world.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.olaz.coreforge.data.Resource;

import java.util.Objects;

public abstract class Tile {
    private TileType type;
    private Texture texture;
    private TileBorder borderTextureRegion;
    private Resource harvestResource;
    private int borderMask;
    private Vector2 position;
    private boolean canInteract = false;
    private boolean hasBlock = false;

    public Tile(TileType type, Texture texture, Vector2 position) {
        this.type = type;
        this.texture = texture;
        this.position = position;

        // Maybe change to switch case if later add more complicated tile type
        canInteract = Objects.requireNonNull(type) != TileType.GROUND;

    }

    public Tile(TileType type, Texture texture, Vector2 position, TileBorder borderTextures) {
        this(type, texture, position);
        setBorderTextureRegion(borderTextures);
    }

    public Tile(TileType type, Texture texture, Vector2 position, TileBorder borderTextures, Resource harvestResource) {
        this(type, texture, position, borderTextures);
        setHarvestResource(harvestResource);
    }

    public abstract void onInteract();

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

    public boolean isCanInteract() {
        return canInteract;
    }

    public void setCanInteract(boolean canInteract) {
        this.canInteract = canInteract;
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
        if (hasBlock) {
            setCanInteract(false);
        }
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

    public Resource getHarvestResource() {
        return harvestResource;
    }

    public void setHarvestResource(Resource harvestResource) {
        this.harvestResource = harvestResource;
    }

    @Override
    public String toString() {
        return "Tile{" +
            "type=" + type +
            ", position=" + position +
            ", canInteract=" + canInteract +
            ", hasBlock=" + hasBlock +
            '}';
    }
}
