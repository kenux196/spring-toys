package designpattern.factory.ex2;

public class SimplePizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equalsIgnoreCase("cheese pizza")) {
            pizza = new CheesePizza();
        } else if (type.equalsIgnoreCase("spicy pizza")) {
            pizza = new SpicyPizza();
        }
        return pizza;
    }
}
