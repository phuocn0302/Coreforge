package com.olaz.coreforge.blocks.machines.ui.components;

import com.badlogic.gdx.graphics.Texture;
import com.olaz.coreforge.blocks.machines.MachineResourceEntry;

public class InputSlot extends Slot {
    private MachineResourceEntry resourceEntry;

    public void addResource(MachineResourceEntry entry) {
        this.resourceEntry = entry;
        if (entry != null && entry.getResource() != null) {
            Texture tex = entry.getResource().getTexture();
            setResourceTexture(tex);
        } else {
            clearResourceTexture();
        }
    }

    public void removeResource() {
        this.resourceEntry = null;
        clearResourceTexture();
    }

    public MachineResourceEntry getResourceEntry() {
        return resourceEntry;
    }
}
