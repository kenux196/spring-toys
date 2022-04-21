package designpattern.observer;

public class WeatherStationTest {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        TemperatureMonitor temperatureMonitor = new TemperatureMonitor(weatherData);

        weatherData.setMeasurements(80f, 65f, 30.4f);
    }
}