package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code.strategy.FlyBehavior;
import me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code.strategy.QuackBehavior;

@Slf4j
public class RubberDuck extends Duck {

    public RubberDuck(String name, FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(name, flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        log.info("{}, display - 노란 고무 오리다.", getName());
    }
}
