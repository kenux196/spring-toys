package me.kenux.playground.pattern.SimDuckApp.SimDuckV1.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RubberDuck extends Duck implements Quackable {

    public RubberDuck(String name) {
        super(name);
    }

    @Override
    public void quack() {
        log.info("{}, 삑삑", getName());
    }

    @Override
    public void display() {
        log.info("{}, display - 노란 고무 오리다.", getName());
    }
}
