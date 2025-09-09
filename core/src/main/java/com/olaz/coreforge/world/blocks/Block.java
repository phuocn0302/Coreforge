package com.olaz.coreforge.world.blocks;

import com.olaz.coreforge.data.Position;
import com.olaz.coreforge.data.Size;

public abstract class Block {
    private final String id;
    private final String description;
    private Size size;
    private Position position;

    public Block(String id, String description, Size size) {
        this.id = id;
        this.description = description;
        this.size = size;
    }

    public Block(String id, String description) {
        this.id = id;
        this.description = description;
        this.size = new Size(1,1);
        this.position = new Position(0,0);
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
