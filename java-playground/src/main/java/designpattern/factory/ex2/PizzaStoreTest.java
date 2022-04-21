package designpattern.factory.ex2;

public class PizzaStoreTest {
    public static void main(String[] args) {

        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();

        PizzaStore pizzaStore = new PizzaStore(simplePizzaFactory);

        Pizza pizza = pizzaStore.orderPizza("cheese pizza");
        pizza.getName();

        Pizza spicyPizza = pizzaStore.orderPizza("spicy pizza");
        spicyPizza.getName();
    }
}
