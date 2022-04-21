package designpattern.factory.ex2;

public class PizzaStore {

    private final SimplePizzaFactory simplePizzaFactory;

    public PizzaStore(SimplePizzaFactory simplePizzaFactory) {
        this.simplePizzaFactory = simplePizzaFactory;
    }

    public Pizza orderPizza(String type) {
        return simplePizzaFactory.createPizza(type);
    }
}
