package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv1.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Duck {

    private final String name;

    public Duck(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void display();

    public void swim() {
        log.info("{}, 수영한다~~~", name);
    }
}
