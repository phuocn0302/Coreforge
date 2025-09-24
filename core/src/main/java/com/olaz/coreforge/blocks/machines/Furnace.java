package com.olaz.coreforge.blocks.machines;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.olaz.coreforge.blocks.IOMachine;
import com.olaz.coreforge.data.Recipe;
import com.olaz.coreforge.data.Size;

public class Furnace extends IOMachine {
    private final int inputSlot = 3;
    private final int outputSlot = 1;

    public Furnace(String id, String description, Texture texture, Size size) {
        super(id, description, texture, size);
    }

    public Furnace(String id, String description, Texture texture) {
        super(id, description, texture);
    }

    @Override
    protected void update() {

    }

    @Override
    public Array<Recipe> getAvailableRecipes() {
        return null;
    }


    @Override
    public int getInputSlotSize() {
        return inputSlot;
    }

    @Override
    public int getOutputSlotSize() {
        return outputSlot;
    }
}
