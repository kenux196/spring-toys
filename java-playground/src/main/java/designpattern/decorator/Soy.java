package designpattern.decorator;

public class Soy extends CondimentDecorator {
    private final Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "두유 추가";
    }

    @Override
    public int cost() {
        return beverage.cost() + 300;
    }
}