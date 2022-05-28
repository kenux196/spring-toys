package me.kenux.playground.pattern.observer.weathersystem.code.display;

import me.kenux.playground.pattern.observer.weathersystem.code.DisplayElement;
import me.kenux.playground.pattern.observer.weathersystem.code.Observer;
import me.kenux.playground.pattern.observer.weathersystem.code.WeatherData;

public class ForecastDisplay implements Observer, DisplayElement {

    private float currentPressure = 29.92f;
    private float lastPressure;
    private WeatherData weatherData;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("기상 예보: ");
        if (currentPressure > lastPressure) {
            System.out.println("날씨가 좋아지고 있습니다.");
        } else if (currentPressure == lastPressure) {
            System.out.println("지금과 비슷할 것 같습니다.");
        } else {
            System.out.println("쌀쌀하며 비 또는 눈이 올것 같습니다.");
        }
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }
}
