package com.olaz.coreforge.world.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.olaz.coreforge.data.Position;

import java.util.Objects;

public abstract class Tile {
    private TileType type;
    private Texture texture;
    private Position position;
    private boolean canInteract = false;
    private boolean hasBlock = false;

    public Tile(TileType type, Texture texture, Position position) {
        this.type = type;
        this.texture = texture;
        this.position = position;

        // Maybe change to switch case if later add more complicated tile type
        canInteract = Objects.requireNonNull(type) != TileType.GROUND;

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
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
}
