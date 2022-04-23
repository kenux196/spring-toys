package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv0.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MallardDuck extends Duck {

    public MallardDuck(String name) {
        super(name);
    }

    @Override
    public void display() {
        log.info("{}, display - 발이 노랗다", getName());
    }
}
