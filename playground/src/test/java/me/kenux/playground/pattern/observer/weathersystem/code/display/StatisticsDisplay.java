package me.kenux.playground.pattern.observer.weathersystem.code.display;

import me.kenux.playground.pattern.observer.weathersystem.code.DisplayElement;
import me.kenux.playground.pattern.observer.weathersystem.code.Observer;
import me.kenux.playground.pattern.observer.weathersystem.code.WeatherData;

public class StatisticsDisplay implements Observer, DisplayElement {

    private float maxTemp = 0f;
    private float minTemp = 200;
    private float tempSum = 0f;
    private int numReadings = 0;
    private WeatherData weatherData;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }

        if (temperature < minTemp) {
            minTemp = temperature;
        }
        display();
    }

    @Override
    public void display() {
        String format = String.format("평균/최고/최저 온도 = %.1f/%.1f/%.1f", tempSum / numReadings, maxTemp, minTemp);
        System.out.println(format);
    }
}
