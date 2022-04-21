package designpattern.decorator;

public class MyCoffeeShop {

    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        System.out.println("espresso = " + espresso.getDescription() + " : " + espresso.cost() + " 원");

        Beverage darkRoast = new DarkRoast();
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Whip(darkRoast);
        System.out.println("darkRoast = " + darkRoast.getDescription() + " : " + darkRoast.cost() + " 원");
    }
}