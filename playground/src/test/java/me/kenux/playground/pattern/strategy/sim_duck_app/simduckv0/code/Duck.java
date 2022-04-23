package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv0.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Duck {

    private String name;

    public Duck(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void display();

    public void quack() {
        log.info("{}, 꽥꽥 ~~", name);
    }

    public void swim() {
        log.info("{}, 수영한다~~~", name);
    }

    public void fly() {
        log.info("{}, 날아간다~~~", name);
    }
}
