package com.olaz.coreforge.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.olaz.coreforge.data.Size;

import java.util.Objects;

public abstract class Block {
    private final String id;
    private final String description;
    private final Texture texture;
    private Size size;

    // For machine identification, static counter increase each instantiate
    private static int staticCounter = 0;
    private final int uniqueCounter;

    public Block(String id, String description, Texture texture, Size size) {
        this.id = id;
        this.description = description;
        this.texture = texture;
        this.size = size;
        this.uniqueCounter = staticCounter++;
    }

    public Block(String id, String description, Texture texture) {
        this.id = id;
        this.description = description;
        this.texture = texture;
        this.size = new Size(1, 1);
        this.uniqueCounter = staticCounter++;
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

    public Texture getTexture() {
        return texture;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(id, block.id) && Objects.equals(description, block.description) && Objects.equals(size, block.size) && Objects.equals(staticCounter, uniqueCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, size, uniqueCounter);
    }
}
