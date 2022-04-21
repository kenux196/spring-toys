package designpattern.decorator;

public class Mocha extends CondimentDecorator {

    private final Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 모카 추가";
    }

    @Override
    public int cost() {
        return beverage.cost() + 600;
    }
}