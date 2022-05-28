package me.kenux.playground.pattern.observer.weathersystem;

import me.kenux.playground.pattern.observer.weathersystem.code.display.CurrentConditionDisplay;
import me.kenux.playground.pattern.observer.weathersystem.code.WeatherData;
import me.kenux.playground.pattern.observer.weathersystem.code.display.ForecastDisplay;
import me.kenux.playground.pattern.observer.weathersystem.code.display.HeatIndexDisplay;
import me.kenux.playground.pattern.observer.weathersystem.code.display.StatisticsDisplay;
import org.junit.jupiter.api.Test;

class WeatherStationTest {

    WeatherData weatherData = new WeatherData();

    @Test
    void currentConditionDisplayTest() {
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        weatherData.setMeasurements(24.3f, 43, 30.4f);
    }

    @Test
    void statisticsDisplayTest() {
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        weatherData.setMeasurements(24, 43, 30.4f);
        weatherData.setMeasurements(25, 43, 30.4f);
        weatherData.setMeasurements(26, 43, 30.4f);
    }

    @Test
    void forecastDisplayTest() {
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setMeasurements(24, 43, 33.4f);
        weatherData.setMeasurements(25, 43, 33.4f);
        weatherData.setMeasurements(26, 43, 31.4f);
    }

    @Test
    void heatIndexDisplay() {
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);
        weatherData.setMeasurements(24, 43, 33.4f);
        weatherData.setMeasurements(25, 43, 33.4f);
        weatherData.setMeasurements(26, 43, 31.4f);
    }

    @Test
    void allTest() {
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setMeasurements(24, 43, 33.4f);
        weatherData.setMeasurements(25, 43, 33.4f);
        weatherData.setMeasurements(26, 43, 31.4f);
    }

}
