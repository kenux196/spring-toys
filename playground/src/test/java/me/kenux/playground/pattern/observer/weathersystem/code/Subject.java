package me.kenux.playground.pattern.observer.weathersystem.code;

public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}
