package designpattern.observer;

public class TemperatureMonitor implements Observer, DisplayElement {
    private float temperature;
    private Subject weatherData;

    public TemperatureMonitor(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("온도 모니터 => temperature = " + temperature);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        display();
    }
}