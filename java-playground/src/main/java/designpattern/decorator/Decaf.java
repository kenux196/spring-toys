package designpattern.decorator;

public class Decaf extends Beverage {
    public Decaf() {
        description = "디카페인 커피";
    }

    @Override
    public int cost() {
        return 5500;
    }
}