package me.kenux.playground.pattern.decorator.starbuzzcoffee;

public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "하우스 블랜드 커피";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
