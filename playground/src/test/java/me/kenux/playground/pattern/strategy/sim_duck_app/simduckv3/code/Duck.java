package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code.strategy.FlyBehavior;
import me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code.strategy.QuackBehavior;

@Slf4j
public abstract class Duck {

    private final String name;
    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public Duck(String name, FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.name = name;
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }

    public String getName() {
        return name;
    }

    public abstract void display();

    public void swim() {
        log.info("{}, 수영한다~~~", name);
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public void changeFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void changeQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
