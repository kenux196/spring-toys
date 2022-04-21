package ooad.pricipal;

public class Automobile {
    private AutomobileStatus status;

    public void start() {
        status = AutomobileStatus.START;
    }

    public void stop() {
        status = AutomobileStatus.STOP;
    }

    public int getOil() {
        return 0;
    }
}