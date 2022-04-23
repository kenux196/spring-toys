package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code.strategy.FlyBehavior;
import me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code.strategy.QuackBehavior;

@Slf4j
public class DecoyDuck extends Duck {

    public DecoyDuck(String name, FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(name, flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        log.info("{}, 나무로 만든 오리", getName());
    }
}
