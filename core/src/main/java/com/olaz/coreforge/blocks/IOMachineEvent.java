package com.olaz.coreforge.blocks;

import com.olaz.coreforge.blocks.machines.MachineResourceEntry;

public class IOMachineEvent {
    public static class InputChanged {
        public final int slot;
        public final MachineResourceEntry entry;
        public InputChanged(int slot, MachineResourceEntry entry) {
            this.slot = slot; this.entry = entry;
        }
    }

    public static class OutputChanged {
        public final int slot;
        public final MachineResourceEntry entry;
        public OutputChanged(int slot, MachineResourceEntry entry) {
            this.slot = slot; this.entry = entry;
        }
    }
}
