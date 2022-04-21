package designpattern.decorator;

public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "하우스블랜드 커피";
    }

    @Override
    public int cost() {
        return 4400;
    }
}