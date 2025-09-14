package com.olaz.coreforge.blocks;

import com.badlogic.gdx.Gdx;
import com.olaz.coreforge.data.Resource;
import com.olaz.coreforge.data.Size;
import com.olaz.coreforge.systems.Tickable;

import java.util.ArrayList;

public class Machine extends Block implements Tickable {
    private ArrayList<Resource> input = new ArrayList<>();
    private float progress;
    private boolean isActive;

    public Machine(String id, String description, Size size) {
        super(id, description, size);
    }

    public Machine(String id, String description) {
        super(id, description);
    }

    @Override
    public void tickUpdate() {
        Gdx.app.log("Machine", this.getId() + "Tick Updated");
    }

    protected void checkInputSlot() {

    }

    protected void checkRecipe() {

    }

    protected void serveOutput() {

    }

    public ArrayList<Resource> getInput() {
        return input;
    }

    public void setInput(ArrayList<Resource> input) {
        this.input = input;
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
