package com.olaz.coreforge.blocks.machines;

import com.olaz.coreforge.data.Resource;

public class MachineResourceEntry {
    private final Resource resource;
    private int quantity;

    public MachineResourceEntry(Resource resource, int quantity) {
        this.resource = resource;
        this.quantity = quantity;
    }

    public Resource getResource() {
        return resource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "MachineResourceEntry{" +
            "\n + Resource=" + resource +
            "\n + Quantity=" + quantity +
            "\n}";
    }
}
