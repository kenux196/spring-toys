package designpattern.decorator;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "다크로스트 커피";
    }

    @Override
    public int cost() {
        return 5000;
    }
}