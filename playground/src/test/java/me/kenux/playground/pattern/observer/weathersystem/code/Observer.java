package me.kenux.playground.pattern.observer.weathersystem.code;

public interface Observer {
    void update(float temperature, float humidity, float pressure);
}
