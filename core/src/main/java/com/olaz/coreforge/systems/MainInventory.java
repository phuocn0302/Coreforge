package com.olaz.coreforge.systems;

import com.olaz.coreforge.data.Resource;

import java.util.HashMap;
import java.util.Map;

public class MainInventory {
    private final Map<Resource, Integer> inventory = new HashMap<>();

    public void addToInventory(Resource resource, int quantity) {
        inventory.merge(resource, quantity, Integer::sum);
    }

    public void removeFromInventory(Resource resource, int quantity) {
        if (inventory.get(resource) == null) {
            throw new IllegalArgumentException("Trying to remove non exist resources from inventory");
        }

        inventory.put(resource, inventory.get(resource) - quantity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MainInventory:\n");
        for (Map.Entry<Resource, Integer> entry : inventory.entrySet()) {
            sb.append("- ")
                .append(entry.getKey().getName())
                .append(": ")
                .append(entry.getValue())
                .append("\n");
        }
        return sb.toString();
    }
}
