package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv1.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedHeadDuck extends Duck implements Flyable, Quackable {

    public RedHeadDuck(String name) {
        super(name);
    }

    @Override
    public void fly() {
        log.info("{}, 날아간다~~~", getName());
    }

    @Override
    public void quack() {
        log.info("{}, 꽥꽥 ~~", getName());
    }

    @Override
    public void display() {
        log.info("{}, display - 머리가 빨갛다", getName());
    }
}
