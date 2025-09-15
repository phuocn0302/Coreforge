package com.olaz.coreforge.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.olaz.coreforge.data.Recipe;
import com.olaz.coreforge.data.Resource;
import com.olaz.coreforge.data.Size;
import com.olaz.coreforge.systems.Tickable;

import java.util.ArrayList;

public abstract class Machine extends Block implements Tickable {
    private ArrayList<Resource> input = new ArrayList<>();
    private Recipe selectedRecipe;
    private float progress;
    private boolean isActive;

    public Machine(String id, String description, Texture texture, Size size) {
        super(id, description, texture, size);
    }

    public Machine(String id, String description, Texture texture) {
        super(id, description, texture);
    }


    @Override
    public void tickUpdate() {
        if (selectedRecipe == null) return;

        // Check if inputs are available
        if (!input.containsAll(selectedRecipe.getInputs())) {
            isActive = false;
            progress = 0;
            return;
        }

        progress += progress += 1f / selectedRecipe.getDuration();

        if (progress >= selectedRecipe.getDuration()) {
            serveOutput();
            progress = 0;
        }
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
