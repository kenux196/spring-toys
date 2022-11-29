package me.kenux.playground.pattern.decorator.starbuzzcoffee;

public abstract class CondimentDecorator extends Beverage {

    Beverage beverage;

    public abstract String getDescription();
}
