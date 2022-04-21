package designpattern.decorator;

public class Whip extends CondimentDecorator {
    private final Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 휘핑 추가";
    }

    @Override
    public int cost() {
        return beverage.cost() + 1000;
    }
}