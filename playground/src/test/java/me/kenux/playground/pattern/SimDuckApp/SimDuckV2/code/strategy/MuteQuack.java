package me.kenux.playground.pattern.SimDuckApp.SimDuckV2.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        log.info("소리내지 못한다.");
    }
}
