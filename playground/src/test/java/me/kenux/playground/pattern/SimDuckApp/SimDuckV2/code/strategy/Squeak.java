package me.kenux.playground.pattern.SimDuckApp.SimDuckV2.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        log.info("삑삑");
    }
}
