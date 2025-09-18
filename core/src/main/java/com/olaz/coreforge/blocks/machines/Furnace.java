package com.olaz.coreforge.blocks.machines;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.olaz.coreforge.blocks.Machine;
import com.olaz.coreforge.data.Recipe;
import com.olaz.coreforge.data.Size;

public class Furnace extends Machine {
    public Furnace(String id, String description, Texture texture, Size size) {
        super(id, description, texture, size);
    }

    public Furnace(String id, String description, Texture texture) {
        super(id, description, texture);
    }

    @Override
    public Array<Recipe> getAvailableRecipes() {
        return null;
    }
}
