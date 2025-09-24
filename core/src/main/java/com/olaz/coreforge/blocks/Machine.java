package com.olaz.coreforge.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.olaz.coreforge.blocks.machines.ui.MachineUI;
import com.olaz.coreforge.data.Recipe;
import com.olaz.coreforge.data.Resource;
import com.olaz.coreforge.data.Size;
import com.olaz.coreforge.systems.Tickable;

public abstract class Machine extends Block implements Tickable {
    private Recipe selectedRecipe;
    private float progress;
    private boolean isActive;

    public Machine(String id, String description, Texture texture, Size size) {
        super(id, description, texture, size);
    }

    public Machine(String id, String description, Texture texture) {
        super(id, description, texture);
    }

    protected abstract void update();

    @Override
    public void tickUpdate() {
        update();
    }

    public abstract Array<Recipe> getAvailableRecipes();

    protected void serveOutput() {

    }

    public void setSelectedRecipe(Recipe recipe) {
        this.selectedRecipe = recipe;
        this.progress = 0;
        this.isActive = false;
    }

    public Recipe getSelectedRecipe() {
        return selectedRecipe;
    }


    public float getProgress() {
        return progress;
    }

    public boolean getActiveStatus() {
        return isActive;
    }

    public void setActiveStatus(boolean active) {
        isActive = active;
    }
}
