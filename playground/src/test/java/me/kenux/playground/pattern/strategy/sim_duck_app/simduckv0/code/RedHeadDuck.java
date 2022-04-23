package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv0.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedHeadDuck extends Duck {

    public RedHeadDuck(String name) {
        super(name);
    }

    @Override
    public void display() {
        log.info("{}, display - 머리가 빨갛다", getName());
    }
}
