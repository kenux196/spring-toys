package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv1.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MallardDuck extends Duck implements Flyable, Quackable {

    public MallardDuck(String name) {
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
        log.info("{}, display - 발이 노랗다", getName());
    }
}
