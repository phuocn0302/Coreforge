package com.olaz.coreforge.utils.observer;

public interface Observer<T> {
    void onEvent(T event);
}
