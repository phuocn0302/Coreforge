package com.olaz.coreforge.data;

import com.badlogic.gdx.utils.Array;

import java.util.List;

public class Recipe {
    public String machine;
    private List<Resource> inputs;
    private final Resource output;
    private final int duration;

    public Recipe(String machine, List<Resource>  inputs, Resource output, int duration) {
        this.machine = machine;
        this.inputs = inputs;
        this.output = output;
        this.duration = duration;
    }

    public String getMachineType() {
        return machine;
    }

    public List<Resource> getInputs() {
        return inputs;
    }

    public Resource getOutput() {
        return output;
    }

    public int getDuration() {
        return duration;
    }
}

