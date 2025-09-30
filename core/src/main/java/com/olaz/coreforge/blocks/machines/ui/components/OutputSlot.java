package com.olaz.coreforge.blocks.machines.ui.components;

import com.olaz.coreforge.blocks.machines.MachineResourceEntry;

public class OutputSlot extends Slot {

    public void addResource(MachineResourceEntry entry) {
        setResource(entry);
    }

    public void removeResource() {
        clearResource();
    }
}
