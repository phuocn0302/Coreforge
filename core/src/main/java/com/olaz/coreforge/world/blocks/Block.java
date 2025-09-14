package com.olaz.coreforge.world.blocks;

import com.badlogic.gdx.math.Vector2;
import com.olaz.coreforge.data.Size;

public abstract class Block {
    private final String id;
    private final String description;
    private Size size;
    private Vector2 position;

    public Block(String id, String description, Size size) {
        this.id = id;
        this.description = description;
        this.size = size;
    }

    public Block(String id, String description) {
        this.id = id;
        this.description = description;
        this.size = new Size(1, 1);
        this.position = new Vector2(0, 0);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
