package designpattern.adapter.turkeyadapter;

public class DuckTest {
    public static void main(String[] args) {
        System.out.println("The Duck say...");
        MallardDuck duck = new MallardDuck();
        duck.quack();
        duck.fly();

        WildTurkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        System.out.println("The turkey says...");
        turkey.gobble();
        turkey.fly();

        System.out.println("The TurkeyAdapter say...");
        turkeyAdapter.quack();
        turkeyAdapter.fly();
    }
}
