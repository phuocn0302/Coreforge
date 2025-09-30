package com.olaz.coreforge.blocks;

import com.olaz.coreforge.blocks.machines.MachineResourceEntry;

public class IOMachineEvent {
    public static class InputAdd {
        public final int slot;
        public final MachineResourceEntry entry;
        public InputAdd(int slot, MachineResourceEntry entry) {
            this.slot = slot; this.entry = entry;
        }
    }

    public static class InputRemove {
        public final int slot;
        public InputRemove(int slot) {
            this.slot = slot;
        }
    }

    public static class OutputCreated {
        public final int slot;
        public final MachineResourceEntry entry;
        public OutputCreated(int slot, MachineResourceEntry entry) {
            this.slot = slot; this.entry = entry;
        }
    }

    public static class OutputRemove {
        public final int slot;
        public OutputRemove(int slot) {
            this.slot = slot;
        }
    }
}
