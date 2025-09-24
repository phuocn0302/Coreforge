package com.olaz.coreforge.blocks.machines;

public interface MachineOutput {
    int getOutputSlotSize();
    void addOutput(int slotIndex, MachineResourceEntry resourceEntry);
    MachineResourceEntry getOutputResourceEntry(int slotIndex);
}
