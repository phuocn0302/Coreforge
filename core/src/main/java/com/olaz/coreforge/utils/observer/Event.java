package com.olaz.coreforge.utils.observer;

import java.util.ArrayList;
import java.util.List;

public class Event<T> {
    private final List<Observer<T>> observerList = new ArrayList<>();

    public void connect(Observer<T> observer) {
        observerList.add(observer);
    }

    public void disconnect(Observer<T> observer) {
        observerList.remove(observer);
    }

    public void emit(T event) {
        for (Observer<T> ob : observerList) {
            ob.onEvent(event);
        }
    }
}
