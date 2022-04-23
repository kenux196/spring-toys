package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv0.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RubberDuck extends Duck {

    public RubberDuck(String name) {
        super(name);
    }

    @Override
    public void quack() {
        log.info("{}, 삑삑", getName());
    }

    @Override
    public void fly() {
        // do nothing~
    }

    @Override
    public void display() {
        log.info("{}, display - 노란 고무 오리다.", getName());
    }
}
