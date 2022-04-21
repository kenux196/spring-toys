package designpattern.decorator;

public abstract class Beverage {
    protected String description = "";

    public String getDescription() {
        return description;
    }

    public abstract int cost();
}