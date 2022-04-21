package me.kenux.playground.pattern.SimDuckApp.SimDuckV1.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoyDuck extends Duck {

    public DecoyDuck(String name) {
        super(name);
    }

    @Override
    public void display() {
        log.info("{}, 나무로 만든 오리", getName());
    }
}
