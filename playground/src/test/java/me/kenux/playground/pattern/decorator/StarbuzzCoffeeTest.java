package me.kenux.playground.pattern.decorator;

import me.kenux.playground.pattern.decorator.starbuzzcoffee.*;
import org.junit.jupiter.api.Test;

class StarbuzzCoffeeTest {

    @Test
    void orderTest() {
        Beverage beverage = new Espresso();
        displayOrder(beverage);

        Beverage beverage1 = new DarkRoast();
        beverage1 = new Mocha(beverage1);
        beverage1 = new Mocha(beverage1);
        beverage1 = new Whip(beverage1);
        displayOrder(beverage1);

        Beverage beverage2 = new HouseBlend();
        beverage2 = new Soy(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        displayOrder(beverage2);
    }

    public void displayOrder(Beverage beverage) {
        System.out.println("beverage.getDescription() = " + beverage.getDescription() + " $" + beverage.cost());
    }
}
