package me.kenux.playground.pattern.observer.weathersystem.code.display;

import me.kenux.playground.pattern.observer.weathersystem.code.DisplayElement;
import me.kenux.playground.pattern.observer.weathersystem.code.Observer;
import me.kenux.playground.pattern.observer.weathersystem.code.WeatherData;

public class CurrentConditionDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    public CurrentConditionDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("현재 상태: 온도 " + temperature + "C, 습도 " + humidity + "%");
    }
}
