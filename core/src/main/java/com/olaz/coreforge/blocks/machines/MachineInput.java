package com.olaz.coreforge.blocks.machines;

public interface MachineInput {
    int getInputSlotSize();
    void addResource(int slotIndex, MachineResourceEntry resourceEntry);
    MachineResourceEntry getInputResourceEntry(int slotIndex);
}
