package com.olaz.coreforge.utils.observer;

import java.util.ArrayList;
import java.util.List;

// Event without type
public class Signal {
    private final List<SignalListener> listeners = new ArrayList<>();

    public void connect(SignalListener listener) {
        listeners.add(listener);
    }

    public void disconnect(SignalListener listener) {
       listeners.remove(listener);
    }

    public void emit() {
        for (SignalListener l : listeners) {
            l.onSignal();
        }
    }
}
