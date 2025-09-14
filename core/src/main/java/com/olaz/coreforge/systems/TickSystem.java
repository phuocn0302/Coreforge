package com.olaz.coreforge.systems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TickSystem {
    private final float tickPerSecond = 3;
    private final Set<Tickable> tickableSet = new HashSet<>();
    private float counter;

    public void update(float delta) {
        counter += delta;
        float tickInterval = 1f / tickPerSecond;

        while (counter >= tickInterval) {
            for (Tickable t : tickableSet) {
                t.tickUpdate();
            }
            counter -= tickInterval;
        }
    }


    public void addToSystem(Tickable tickable) {
        tickableSet.add(tickable);
    }

    public void removeFromSystem(Tickable tickable) {
        tickableSet.remove(tickable);
    }
}
