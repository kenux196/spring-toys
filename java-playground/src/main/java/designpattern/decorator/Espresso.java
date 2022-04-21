package designpattern.decorator;

public class Espresso extends Beverage {

    public Espresso() {
        description = "에스프레소 커피";
    }

    @Override
    public int cost() {
        return 4000;
    }
}